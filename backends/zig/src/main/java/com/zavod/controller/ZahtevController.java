package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.model.Zahtev;
import com.zavod.service.ZigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zahtev")
public class ZahtevController {

    @Autowired
    public ZigService zigService;

    @PostMapping(path = "/", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN')")
    public ResponseOk createNew(@RequestBody Zahtev zahtev) {
        zigService.addZahtev(zahtev);
        return new ResponseOk("Zahtev kreiran.");
    }



}
