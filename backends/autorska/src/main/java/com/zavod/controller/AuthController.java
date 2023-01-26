package com.zavod.controller;

import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import com.zavod.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    public AuthService authService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public TokenDTO login(@RequestBody Kredencijali kredencijali) {
        return authService.login(kredencijali);
    }

}
