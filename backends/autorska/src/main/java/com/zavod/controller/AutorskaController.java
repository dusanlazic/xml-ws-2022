package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import com.zavod.service.AutorskaService;
import com.zavod.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/autorska")
public class AutorskaController {

    @Autowired
    public AutorskaService autorskaService;

    @Autowired
    public PDFService pdfService;

    @GetMapping(path = "/all")
    public Zahtevi getAll() {
        return autorskaService.getAll();
    }

    @PostMapping(path = "/add", consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseOk addZahtev(@RequestBody TZahtev zahtev) {
        autorskaService.addZahtev(zahtev);
        return new ResponseOk("Zahtev kreiran");
    }

    @GetMapping(path = "/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public TZahtev getFirst() {
        return autorskaService.getAll().getZahtev().get(0);
    }

    @GetMapping(path = "/last", produces = {MediaType.APPLICATION_XML_VALUE})
    public TZahtev getLast() {
        List<TZahtev> zahtevi = autorskaService.getAll().getZahtev();
        if (zahtevi.isEmpty())
            return null;
        return zahtevi.get(zahtevi.size() - 1);
    }

    @GetMapping(path = "pdf")
    public void generatePdf() {
        pdfService.generateFiles(autorskaService.getAll().getZahtev().get(0));
    }
}
