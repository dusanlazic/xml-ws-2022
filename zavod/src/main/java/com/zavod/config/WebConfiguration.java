package com.zavod.config;


import com.zavod.annotation.XmlAutorskaMessageConverter;
import com.zavod.annotation.XmlZigMessageConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        httpMessageConverters.add(new XmlAutorskaMessageConverter(MediaType.APPLICATION_XML));
        httpMessageConverters.add(new XmlZigMessageConverter(MediaType.APPLICATION_XML));
    }
}
