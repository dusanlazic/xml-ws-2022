package com.zavod;

import com.zavod.repository.AutorskaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableWebMvc
public class AutorskaApplication {

	@Autowired
	private AutorskaRepository autorskaRepository;

	public static void main(String[] args) {
		SpringApplication.run(AutorskaApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		autorskaRepository.load();
	}
}
