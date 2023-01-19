package com.zavod.repository;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.zavod.model.Zahtev;
import com.zavod.util.AuthenticationUtilities;
import com.zavod.util.MarshallingService;
import com.zavod.util.SparqlUtil;
import lombok.var;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Repository;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Repository
public class MetadataRepository {

    private static final String XSLT_FILE = "src/main/resources/xslt/zahtev-meta.xsl";
    private static final String SPARQL_NAMED_GRAPH_URI = "/zahtevi/metadata";

    String rdfFilePath = "src/main/resources/gen/metadata.rdf";

    AuthenticationUtilities.FusekiConnectionProperties conn;

    public MetadataRepository() {
        try {
            conn = AuthenticationUtilities.loadFusekiProperties();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void extractMetadata(InputStream in, OutputStream out) throws FileNotFoundException, TransformerException {
        StreamSource transformSource = new StreamSource(new File(XSLT_FILE));
        var transformerFactory = new TransformerFactoryImpl();
        Transformer transformer = transformerFactory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamSource source = new StreamSource(in);
        StreamResult result = new StreamResult(out);
        transformer.transform(source, result);
    }

    public void writeRdf(String rdf) {
        // Loading a default model with extracted metadata
        Model model = ModelFactory.createDefaultModel();
        ByteArrayInputStream is = new ByteArrayInputStream(rdf.getBytes(StandardCharsets.UTF_8));

        model.read(is, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        // Writing the named graph
        System.out.println("[INFO] Populating named graph \"" + SPARQL_NAMED_GRAPH_URI + "\" with extracted metadata.");
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();


        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close() ;

        System.out.println("[INFO] End.");
    }

    public String loadRdf(Zahtev zahtev) throws FileNotFoundException, TransformerException {
        var ms = new MarshallingService<Zahtev>(Zahtev.class);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ms.marshall(zahtev, os);
        String conv = os.toString();
        System.out.println(conv);
        InputStream stream = new ByteArrayInputStream(conv.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream osout = new ByteArrayOutputStream();
        extractMetadata(stream, osout);
        return osout.toString();
    }
}
