package com.zavod.security;

import com.zavod.dto.KorisnikDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class TokenReader {

    public Claims readClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(TokenProvider.key).build();
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
