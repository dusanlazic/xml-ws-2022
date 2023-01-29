package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.KorisnikRegisterDTO;
import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import com.zavod.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public TokenDTO login(@RequestBody Kredencijali kredencijali) {
        return authService.login(kredencijali);
    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseOk register(@RequestBody KorisnikRegisterDTO korisnik) {
        authService.register(korisnik);
        return new ResponseOk("Korisnik kreiran.");
    }

    @GetMapping(path = "/me", produces = MediaType.APPLICATION_XML_VALUE)
    public KorisnikDTO me(Authentication authentication) {
        return (KorisnikDTO) authentication.getPrincipal();
    }

}
