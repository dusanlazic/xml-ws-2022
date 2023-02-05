package com.zavod.service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.zavod.model.izvestaj.Izvestaj;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.util.MarshallingService;
import com.zavod.util.QRCodeEncoder;
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

import static com.zavod.util.ServiceUtil.brojToHumanReadable;
import static com.zavod.util.ServiceUtil.df;

@Service
public class PDFService {
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

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

    public org.w3c.dom.Document buildDocument(Izvestaj izvestaj) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            MarshallingService<Izvestaj> marshallingService = new MarshallingService<>(Izvestaj.class);
            String marshalled = marshallingService.marshallString(izvestaj);
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

    public void generateHTML(Zahtev zahtev, String htmlFilename, String qrCodeImageUrl) throws FileNotFoundException {
        try {
            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File("src/main/resources/xslt/zahtev.xsl"));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            transformer.setParameter("qr_code_image", qrCodeImageUrl);
            transformer.setParameter("primerak_znaka", zahtev.getInformacijeZavoda().getPrilozi().getPrimerakZnaka().getPutanja());
            transformer.setParameter("broj_klasa", zahtev.getZig().getKlaseRobe().getKlasa().size());

            // Transform DOM to HTML
            zahtev.getInformacijeZavoda().setBrojPrijave(brojToHumanReadable(zahtev.getInformacijeZavoda().getBrojPrijave()));
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

    public void generateHTML(Izvestaj izvestaj, String htmlFilename) {
        try {
            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File("src/main/resources/xslt/izvestaj.xsl"));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(izvestaj));
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
            String qrCodeImageUrl = "http://localhost:8082/zahtevi/qr/" + brojPrijave + ".png";
            generateHTML(zahtev, htmlFilename, qrCodeImageUrl);

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

    public ResponseEntity<Resource> exportToResource(Izvestaj izvestaj) {
        File pdfFile = new File(OUTPUT_DIR);
        if (!pdfFile.getParentFile().exists())
            pdfFile.getParentFile().mkdir();

        try {
            String datumOd = df.format(izvestaj.getDatumOd().toGregorianCalendar().getTime());
            String datumDo = df.format(izvestaj.getDatumDo().toGregorianCalendar().getTime());

            String baseFilename = HTML_DIR + "izvestaj_" + datumOd + "_" + datumDo;
            String htmlFilename = baseFilename + ".html";
            String pdfFilename = baseFilename + ".pdf";

            generateHTML(izvestaj, htmlFilename);
            generatePDF(pdfFilename, htmlFilename);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new UrlResource(Paths.get(pdfFilename).toUri()));

        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<byte[]> qrCodeToResource(String brojPrijave) throws BadElementException, IOException, WriterException {
        String url = "http://localhost:4200/zig/zahtev/" + brojPrijave;
        return QRCodeEncoder.generateQRCodeImage(url);
    }
}
