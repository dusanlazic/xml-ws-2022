package com.zavod;

import com.zavod.model.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZigRepository;
import com.zavod.util.MarshallingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


@SpringBootApplication
@EnableWebMvc
public class ZigApplication {

	@Autowired
	private ZigRepository zigRepository;

	@Autowired
	private MetadataRepository metadataRepository;

	public static void main(String[] args) {
		SpringApplication.run(ZigApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		zigRepository.load();
		List<Zahtev> zahtevi = zigRepository.getAll();
		for (Zahtev zahtev: zahtevi) {
			try {
				String rdf = metadataRepository.loadRdf(zahtev);
				metadataRepository.writeRdf(rdf);
			} catch (FileNotFoundException | TransformerException e) {
				throw new RuntimeException(e);
			}
		}
	}



}
