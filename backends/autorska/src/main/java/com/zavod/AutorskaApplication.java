package com.zavod;

import com.zavod.model.Zahtevi;
import com.zavod.repository.AutorskaRepository;
import com.zavod.repository.MetadataRepository;
import com.zavod.util.AuthenticationUtilities;
import com.zavod.util.MarshallingService;
import com.zavod.util.SparqlUtil;
import lombok.var;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.xml.sax.SAXException;
import org.apache.jena.rdf.model.Model;


import javax.annotation.PostConstruct;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableWebMvc
public class AutorskaApplication {

	@Autowired
	private AutorskaRepository autorskaRepository;

	@Autowired
	private MetadataRepository metadataRepository;

	public static void main(String[] args) {
		SpringApplication.run(AutorskaApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		autorskaRepository.load();
		Zahtevi zahtevi = autorskaRepository.getAll();
		String rdf = metadataRepository.loadRdf(zahtevi);
		metadataRepository.writeRdf(rdf);
	}

}
