package com.zavod.config;


import com.zavod.dto.MetaSearchRequest;
import com.zavod.dto.SearchRequest;
import com.zavod.dto.Zahtevi;
import com.zavod.model.Zahtev;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
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
                Zahtevi.class
        ).forEach(c -> {
            converters.add(0, new XmlGenericConverter<>(c, MediaType.APPLICATION_XML));
        });
    }

}
