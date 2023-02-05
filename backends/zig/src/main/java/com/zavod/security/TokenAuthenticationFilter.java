package com.zavod.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zavod.api.ResponseError;
import com.zavod.dto.KorisnikDTO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenReader tokenReader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = readTokenFromRequest(request);

        if (!StringUtils.hasLength(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = tokenReader.readClaims(token);
            KorisnikDTO korisnik = tokenReader.readUserData(claims);
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(korisnik.getUloga().toUpperCase());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    korisnik,
                    null,
                    Collections.singleton(authority)
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error has occurred.");
            e.printStackTrace();
        }
    }

    private String readTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer "))
            return authHeader.substring(7);

        return null;
    }

    private void sendResponse(HttpServletResponse response, Integer status, String message) throws IOException {
        ResponseError responseError = new ResponseError(status, message);
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_XML_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), responseError);
    }
}
