package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.Korisnici;
import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import com.zavod.model.Korisnik;
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

    @GetMapping(path = "/dev/all", produces = MediaType.APPLICATION_XML_VALUE)
    public Korisnici getAll() {
        return new Korisnici(korisniciService.getAll());
    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseOk register(@RequestBody Korisnik zahtev) {
        korisniciService.register(zahtev);
        System.out.println(zahtev);
        return new ResponseOk("Korisnik kreiran");
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public TokenDTO login(@RequestBody Kredencijali kredencijali) {
        return korisniciService.login(kredencijali);
    }

    @GetMapping(path = "{id}")
    public KorisnikDTO getById(@PathVariable String id) throws XMLDBException {
        return new KorisnikDTO(korisniciService.getKorisnik(id));
    }

    @GetMapping(path = "/dev/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public Korisnik getFirst() {
        return korisniciService.getAll().get(0);
    }

    @GetMapping(path = "/dev/last", produces = {MediaType.APPLICATION_XML_VALUE})
    public Korisnik getLast() {
        List<Korisnik> korisnici = korisniciService.getAll();
        if (korisnici.isEmpty())
            return null;
        return korisnici.get(korisnici.size() - 1);
    }
}
