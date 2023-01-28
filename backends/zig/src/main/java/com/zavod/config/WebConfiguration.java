package com.zavod.config;


import com.zavod.dto.*;
import com.zavod.model.resenje.Resenje;
import com.zavod.model.zahtev.Zahtev;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        Arrays.asList(
                MetaSearchRequest.class,
                SearchRequest.class,
                Zahtev.class,
                Zahtevi.class,
                Kredencijali.class,
                KorisnikDTO.class,
                KorisnikRegisterDTO.class,
                TokenDTO.class,
                Resenje.class,
                ResenjeDTO.class
        ).forEach(c -> {
            converters.add(0, new XmlGenericConverter<>(c, MediaType.APPLICATION_XML));
        });
    }

    private final String[] allowedOrigins = {"http://localhost:4200", "http://localhost:8080"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
