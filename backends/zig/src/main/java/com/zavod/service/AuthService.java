package com.zavod.service;

import com.google.common.net.HttpHeaders;
import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import com.zavod.exception.WrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    public TokenDTO login(Kredencijali kredencijali) {
        WebClient webClient = WebClient.create("http://localhost:8083/auth/login");

        return webClient.post()
                .bodyValue(kredencijali)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                .retrieve()
                .onStatus(
                        HttpStatus.UNAUTHORIZED::equals,
                        response -> Mono.error(WrongCredentialsException::new))
                .bodyToMono(TokenDTO.class)
                .block();
    }
}
