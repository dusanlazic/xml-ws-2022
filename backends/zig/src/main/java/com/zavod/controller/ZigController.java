package com.zavod.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.zavod.api.ResponseOk;
import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.dto.SearchRequest;
import com.zavod.dto.Zahtevi;
import com.zavod.model.Zahtev;
import com.zavod.service.MetadataService;
import com.zavod.service.PDFService;
import com.zavod.service.ZigService;
import com.zavod.util.QRCodeEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/zig")
public class ZigController {

    @Autowired
    public ZigService zigService;

    @Autowired
    public MetadataService metadataService;

    @Autowired
    public PDFService pdfService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtevi getAll() {
        return new Zahtevi(zigService.getAll());
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseOk addZahtev(@RequestBody Zahtev zahtev) {
        System.out.println("Heeeeerre");
        zigService.addZahtev(zahtev);
        System.out.println(zahtev);
        return new ResponseOk("Zahtev kreiran");
    }

    @GetMapping(path = "/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getFirst() {
        return zigService.getAll().get(0);
    }

    @GetMapping(path = "/last", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getLast() {
        List<Zahtev> zahtevi = zigService.getAll();
        if (zahtevi.isEmpty())
            return null;
        return zahtevi.get(zahtevi.size() - 1);
    }

    @PostMapping(path = "search", produces = {MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE})
    public Zahtevi search(@RequestBody SearchRequest searchRequest) {
        if(searchRequest.getQuery().size() == 0) return new Zahtevi();
        return new Zahtevi(zigService.search(searchRequest.getQuery()));
    }

    @PostMapping(path = "search-meta", produces = {MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE})
    public Zahtevi metaSearch(@RequestBody MetaSearchRequest metaSearchRequest) throws XMLDBException {
        for (MetaSearchQuery searchQuery: metaSearchRequest.getQuery()) {
            System.out.println(searchQuery.getPredicate() + " " + searchQuery.getObject() + " " + searchQuery.getOperator());
        }
        return new Zahtevi(metadataService.metaSearch(metaSearchRequest));
    }

    @GetMapping(path = "{id}")
    public Zahtev getZahtev(@PathVariable String id) throws XMLDBException {
        return zigService.getZahtev(id);
    }


    @GetMapping(path = "pdf")
    public ResponseEntity<ResponseOk> generatePdf() {
        String filename = pdfService.generateFiles(zigService.getAll().get(0));
        String url = "http://localhost:8082/zig/dokumenti/" + filename;
        return ResponseEntity
                .created(URI.create(url))
                .body(new ResponseOk("PDF created at " + url));
    }

    @GetMapping(path = "/dokumenti/{filename}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> serve(@PathVariable String filename) throws IOException {
        return pdfService.serve(filename);
    }

    @GetMapping(path = "/dokumenti/{uuid}/qr.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQR(@PathVariable UUID uuid) throws IOException, BadElementException, WriterException {
        String url = "http://localhost:8082/zig/dokumenti/" + uuid + ".pdf";
        return QRCodeEncoder.generateQRCodeImage(url);
    }
}
