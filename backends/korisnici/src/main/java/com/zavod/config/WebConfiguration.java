package com.zavod.config;


import com.zavod.dto.Korisnici;
import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
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
                Korisnici.class,
                Kredencijali.class,
                KorisnikDTO.class,
                TokenDTO.class
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
