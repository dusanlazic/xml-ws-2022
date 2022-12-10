package com.zavod.controller;

import com.sun.xml.xsom.impl.Ref;
import com.zavod.annotation.XmlRequestBody;
import com.zavod.model.autorska.TZahtev;
import com.zavod.model.autorska.Zahtevi;
import com.zavod.service.AutorskaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/autorska")
public class AutorskaController {

    @Autowired
    public AutorskaService autorskaService;

    @GetMapping(path = "/all")
    public Zahtevi getAll() {
        return autorskaService.getAll();
    }

    @PostMapping(path = "/add", consumes = {MediaType.APPLICATION_XML_VALUE})
    public void addZahtev(@RequestBody TZahtev zahtev) {
        autorskaService.addZahtev(zahtev);
    }

    @GetMapping(path = "/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public TZahtev getFirst() {
        return autorskaService.getAll().getZahtev().get(0);
    }
}
