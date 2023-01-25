package com.zavod.service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zavod.model.Zahtev;
import com.zavod.util.MarshallingService;
import com.zavod.util.XUpdateUtil;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

@Service
public class PDFService {
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String XSL_FILE = "data/xsl/zahtev.xsl";

    public static final String OUTPUT_DIR = "src/main/resources/gen/";

    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }

    public void generatePDF(String filePath, String htmlPath) throws IOException, DocumentException {

        // Step 1
        Document document = new Document();

        // Step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Step 3
        document.open();

        // Step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, Files.newInputStream(Paths.get(htmlPath)));

        // Step 5
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

    public void generateHTML(Zahtev zahtev, String xslPath, String outputPath) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(zahtev));
            StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(outputPath)));
            System.out.println(source);
            System.out.println(result);
            transformer.transform(source, result);

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void generateFiles(Zahtev zahtev) {
        String outputDir = OUTPUT_DIR + zahtev.getInformacijeZavoda().getBrojPrijave() + "/";
        String htmlPath = outputDir + "zahtev.html";
        String pdfPath = outputDir + "zahtev.pdf";

        File pdfFile = new File(pdfPath);

        if (!pdfFile.getParentFile().exists()) {
            System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
            pdfFile.getParentFile().mkdir();
        }

        try {
            generateHTML(zahtev, XSL_FILE, htmlPath);
            generatePDF(pdfPath, htmlPath);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[INFO] File \"" + htmlPath + "\" and \"" + pdfPath + "\" generated successfully.");

    }

}
