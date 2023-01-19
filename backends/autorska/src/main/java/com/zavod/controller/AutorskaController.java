package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.Zahtevi;
import com.zavod.model.Zahtev;
import com.zavod.service.AutorskaService;
import com.zavod.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/autorska")
public class AutorskaController {

    @Autowired
    public AutorskaService autorskaService;

    @Autowired
    public PDFService pdfService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtevi getAll() {
        return new Zahtevi(autorskaService.getAll());
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseOk addZahtev(@RequestBody Zahtev zahtev) {
        System.out.println("Heeeeerre");
        autorskaService.addZahtev(zahtev);
        System.out.println(zahtev);
        return new ResponseOk("Zahtev kreiran");
    }

    @GetMapping(path = "/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getFirst() {
        return autorskaService.getAll().get(0);
    }

    @GetMapping(path = "/last", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getLast() {
        List<Zahtev> zahtevi = autorskaService.getAll();
        if (zahtevi.isEmpty())
            return null;
        return zahtevi.get(zahtevi.size() - 1);
    }


    @GetMapping(path = "pdf")
    public void generatePdf(String id) {
        if(id == null || id.isEmpty()) {
            id = autorskaService.getAll().get(0).getInformacijeZavoda().getBrojPrijave();
        }
        pdfService.generateFiles(autorskaService.getZahtev(id));
    }
}
