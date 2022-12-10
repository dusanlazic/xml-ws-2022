package com.zavod.controller;

import com.zavod.model.zig.TZahtev;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/zig")
public class ZigController {

    @PostMapping(path = "/add", consumes = {MediaType.APPLICATION_XML_VALUE})
    public void addZahtev(@RequestBody TZahtev zahtev) {
        System.out.println(zahtev.getZig().getOpis());

    }
}
