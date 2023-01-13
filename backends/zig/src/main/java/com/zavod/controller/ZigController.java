package com.zavod.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.zavod.api.ResponseOk;
import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import com.zavod.service.PDFService;
import com.zavod.service.ZigService;
import com.zavod.util.QRCodeEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public PDFService pdfService;

    @GetMapping(path = "/all")
    public Zahtevi getAll() {
        return zigService.getAll();
    }

    @PostMapping(path = "/add", consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseOk addZahtev(@RequestBody TZahtev zahtev) {
        zigService.addZahtev(zahtev);
        return new ResponseOk("Zahtev kreiran");
    }

    @GetMapping(path = "/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public TZahtev getFirst() {
        return zigService.getAll().getZahtev().get(0);
    }

    @GetMapping(path = "/last", produces = {MediaType.APPLICATION_XML_VALUE})
    public TZahtev getLast() {
        List<TZahtev> zahtevi = zigService.getAll().getZahtev();
        if (zahtevi.isEmpty())
            return null;
        return zahtevi.get(zahtevi.size() - 1);
    }

    @GetMapping(path = "pdf")
    public ResponseEntity<ResponseOk> generatePdf() {
        String filename = pdfService.generateFiles(zigService.getAll().getZahtev().get(0));
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
