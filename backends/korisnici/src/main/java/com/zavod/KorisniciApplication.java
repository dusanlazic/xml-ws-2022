package com.zavod;

import com.zavod.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
public class KorisniciApplication {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(KorisniciApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		korisnikRepository.load();
	}



}
