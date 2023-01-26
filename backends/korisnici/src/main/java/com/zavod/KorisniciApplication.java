package com.zavod;

import com.zavod.repository.KorisniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableWebMvc
public class KorisniciApplication {

	@Autowired
	private KorisniciRepository korisniciRepository;

	public static void main(String[] args) {
		SpringApplication.run(KorisniciApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		korisniciRepository.load();
	}



}
