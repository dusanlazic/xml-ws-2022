package com.zavod;

import com.zavod.repository.AutorskaRepository;
import com.zavod.repository.ZigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableWebMvc
public class ZavodApplication {

	@Autowired
	private AutorskaRepository autorskaRepository;

	@Autowired
	private ZigRepository zigRepository;

	public static void main(String[] args) {
		SpringApplication.run(ZavodApplication.class, args);
	}

	@PostConstruct
	public void init() {
		autorskaRepository.load();
		zigRepository.load();
	}

}
