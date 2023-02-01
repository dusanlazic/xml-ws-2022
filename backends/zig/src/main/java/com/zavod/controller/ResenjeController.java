package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.Resenje;
import com.zavod.service.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;

@RestController
@RequestMapping("/resenja")
public class ResenjeController {

    @Autowired
    private ResenjeService resenjeService;

    @PostMapping(path = "/{brojPrijave}", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public ResponseOk addResenje(@PathVariable String brojPrijave, @RequestBody ResenjeDTO resenje, Authentication authentication) throws XMLDBException, DatatypeConfigurationException {
        resenjeService.addResenje(resenje, brojPrijave, authentication);
        return new ResponseOk("Resenje podneto.");
    }

    @GetMapping(path = "/{brojPrijave}", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public Resenje getResenje(@PathVariable String brojPrijave) throws XMLDBException {
        return resenjeService.getResenje(brojPrijave);
    }

    @GetMapping(path = "/qr/{brojPrijave}.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> qr(@PathVariable String brojPrijave) throws Exception {
        return resenjeService.qrCodeToResource(brojPrijave);
    }
}
