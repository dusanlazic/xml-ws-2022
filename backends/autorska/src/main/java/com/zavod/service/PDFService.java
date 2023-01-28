package com.zavod.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.util.MarshallingService;
import com.zavod.util.XUpdateUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PDFService {
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String XSL_FILE = "data/xsl/zahtev.xsl";

    public static final String HTML_DIR = "gen/itext/";

    public static final String OUTPUT_DIR = "gen/output/";

    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }

    public void generatePDF(String pdfFilename, String htmlFilename) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilename));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlFilename));
        document.close();
    }

    public org.w3c.dom.Document buildDocument(Zahtev zahtev) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            MarshallingService<Zahtev> marshallingService = new MarshallingService<>(Zahtev.class);
            String marshalled = marshallingService.marshallString(zahtev);
            marshalled = XUpdateUtil.clipStringTwo(marshalled);
            System.out.println(marshalled);
            InputSource is = new InputSource(new ByteArrayInputStream(marshalled.getBytes()));
            document = builder.parse(is);

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

        return document;
    }

    public void generateHTML(Zahtev zahtev, String xslPath, String htmlFilename) throws FileNotFoundException {
        try {
            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            zahtev.getInformacijeZavoda().setBrojPrijave(zahtev.getInformacijeZavoda().getBrojPrijave());
            DOMSource source = new DOMSource(buildDocument(zahtev));
            StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(htmlFilename)));
            System.out.println(source);
            System.out.println(result);
            transformer.transform(source, result);

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<Resource> exportToResource(Zahtev zahtev, MediaType type) {
        File pdfFile = new File(OUTPUT_DIR);
        if (!pdfFile.getParentFile().exists())
            pdfFile.getParentFile().mkdir();

        try {
            String brojPrijave = zahtev.getInformacijeZavoda().getBrojPrijave();
            String htmlFilename = HTML_DIR + brojPrijave + ".html";
            generateHTML(zahtev, XSL_FILE, htmlFilename);

            Path resourcePath;
            if (type.equals(MediaType.APPLICATION_PDF)) {
                String pdfFilename = OUTPUT_DIR + brojPrijave + ".pdf";
                generatePDF(pdfFilename, htmlFilename);
                resourcePath = Paths.get(pdfFilename);
            } else {
                resourcePath = Paths.get(htmlFilename);
            }

            return ResponseEntity.ok()
                    .contentType(type)
                    .body(new UrlResource(resourcePath.toUri()));

        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
