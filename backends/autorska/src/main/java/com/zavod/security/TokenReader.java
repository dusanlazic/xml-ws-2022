package com.zavod.security;

import com.zavod.dto.KorisnikDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class TokenReader {

    private final Key key = Keys.hmacShaKeyFor("jtMmX9PRZYT7wmqLLB6M7!xhsJFhSC1$ZSuK0RtHk2Ghx#47Nx".getBytes(StandardCharsets.UTF_8));

    public Claims readClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
        return parser.parseClaimsJws(token).getBody();
    }

    public KorisnikDTO readUserData(Claims claims) {
        return new KorisnikDTO(
                claims.get("id", Long.class),
                claims.get("email", String.class),
                claims.get("ime", String.class),
                claims.get("prezime", String.class),
                claims.get("uloga", String.class)
        );
    }

}
