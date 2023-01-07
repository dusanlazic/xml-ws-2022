package com.zavod;

import com.zavod.repository.AutorskaRepository;
import com.zavod.repository.MetadataRepository;
import com.zavod.util.AuthenticationUtilities;
import com.zavod.util.SparqlUtil;
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

@SpringBootApplication
@EnableWebMvc
public class AutorskaApplication {

	@Autowired
	private AutorskaRepository autorskaRepository;

	@Autowired
	private MetadataRepository metadataRepository;

	private static final String SPARQL_NAMED_GRAPH_URI = "/zahtevi/metadata";


	public static void main(String[] args) {
		SpringApplication.run(AutorskaApplication.class, args);
	}

	@PostConstruct
	public void init() throws Exception {
		autorskaRepository.load();
		run(AuthenticationUtilities.loadFusekiProperties());
	}

	public void run(AuthenticationUtilities.FusekiConnectionProperties conn) throws IOException, SAXException, TransformerException {


		// Referencing XML file with RDF data in attributes
		String xmlFilePath = "src/main/resources/xml/a1.xml";

		String rdfFilePath = "src/main/resources/gen/metadata.rdf";

		// Automatic extraction of RDF triples from XML file

		System.out.println("[INFO] Extracting metadata from RDFa attributes...");
		metadataRepository.extractMetadata(
				new FileInputStream(new File(xmlFilePath)),
				new FileOutputStream(new File(rdfFilePath)));


		// Loading a default model with extracted metadata
		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		model.write(out, SparqlUtil.NTRIPLES);

		System.out.println("[INFO] Extracted metadata as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);


		// Writing the named graph
		System.out.println("[INFO] Populating named graph \"" + SPARQL_NAMED_GRAPH_URI + "\" with extracted metadata.");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));
		System.out.println(sparqlUpdate);

		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();


		// Read the triples from the named graph
		System.out.println();
		System.out.println("[INFO] Retrieving triples from RDF store.");
		System.out.println("[INFO] Using \"" + SPARQL_NAMED_GRAPH_URI + "\" named graph.");

		System.out.println("[INFO] Selecting the triples from the named graph \"" + SPARQL_NAMED_GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");

		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);


		// Query the collection, dump output response as XML
		ResultSet results = query.execSelect();

		ResultSetFormatter.out(System.out, results);

		query.close() ;

		System.out.println("[INFO] End.");
	}
}
