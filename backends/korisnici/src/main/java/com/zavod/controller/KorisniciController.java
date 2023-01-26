package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.Zahtevi;
import com.zavod.model.Zahtev;
import com.zavod.service.KorisniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.util.List;

@RestController
@RequestMapping(path = "/korisnici")
public class KorisniciController {

    @Autowired
    public KorisniciService korisniciService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtevi getAll() {
        return new Zahtevi(korisniciService.getAll());
    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseOk register(@RequestBody Zahtev zahtev) {
        korisniciService.addKorisnik(zahtev);
        System.out.println(zahtev);
        return new ResponseOk("Korisnik kreiran");
    }

    @GetMapping(path = "{id}")
    public Zahtev getById(@PathVariable String id) throws XMLDBException {
        return korisniciService.getKorisnik(id);
    }

    @GetMapping(path = "/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getFirst() {
        return korisniciService.getAll().get(0);
    }

    @GetMapping(path = "/last", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getLast() {
        List<Zahtev> zahtevi = korisniciService.getAll();
        if (zahtevi.isEmpty())
            return null;
        return zahtevi.get(zahtevi.size() - 1);
    }
}
