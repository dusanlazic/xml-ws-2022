package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import com.zavod.service.ZigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/zig")
public class ZigController {

    @Autowired
    public ZigService zigService;

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
    
}
