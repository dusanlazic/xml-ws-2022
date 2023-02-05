package com.zavod.security;

import com.zavod.model.Korisnik;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenProvider {

    public static final Key key = Keys.hmacShaKeyFor("jtMmX9PRZYT7wmqLLB6M7!xhsJFhSC1$ZSuK0RtHk2Ghx#47Nx".getBytes(StandardCharsets.UTF_8));

    public String createAccessToken(Korisnik korisnik) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(10, ChronoUnit.DAYS);

        return Jwts.builder()
                .setSubject(String.valueOf(korisnik.getId()))
                .setIssuedAt(Date.from(now))
                .claim("id", korisnik.getId())
                .claim("email", korisnik.getEmail())
                .claim("ime", korisnik.getIme())
                .claim("prezime", korisnik.getPrezime())
                .claim("uloga", korisnik.getUloga())
                .setExpiration(Date.from(expiresAt))
                .signWith(key)
                .compact();
    }
}
