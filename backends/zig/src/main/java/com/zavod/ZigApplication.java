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
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;


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
//		zigRepository.load();
//		Zahtevi zahtevi = zigRepository.getAll();
//		String rdf = metadataRepository.loadRdf(zahtevi);
//		System.out.println(rdf);
//		metadataRepository.writeRdf(rdf);

		MarshallingService<Zahtev> marshallingService = new MarshallingService<Zahtev>(Zahtev.class);

		FileInputStream fs = new FileInputStream("data/instance1.xml");

		Zahtev zahtev = marshallingService.unmarshall(fs);
		System.out.println(zahtev);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshallingService.marshall(zahtev, baos);

		System.out.println(baos.toString());
	}



}
