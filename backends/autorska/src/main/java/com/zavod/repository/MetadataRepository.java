package com.zavod.repository;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.util.AuthenticationUtilities;
import com.zavod.util.MarshallingService;
import com.zavod.util.SparqlUtil;
import lombok.var;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class MetadataRepository {

    private static final String XSLT_FILE = "src/main/resources/xslt/zahtev-meta.xsl";
    private static final String SPARQL_NAMED_GRAPH_URI = "/zahtevi/metadata";

    String rdfFilePath = "src/main/resources/gen/metadata.rdf";

    String namespace = "http://www.zavod.com/Autorska/";

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


    public Model executeDescribeQuery(String sparqlQuery, OutputStream os) {
        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        Model model = query.execDescribe();
        model.write(os, SparqlUtil.RDF_XML);
        return model;
    }

    public ResultSet executeSelectQuery(String sparqlQuery, OutputStream os) {
        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();
        ResultSetFormatter.outputAsJSON(os, results);
        return results;
    }

    public void updateRdf(String subject, String predicate, String object) {
        UpdateProcessor processor;

        String deleteTriple = String.format("<%s> <%s> ?anyObject", subject, predicate);
        String sparqlDelete = SparqlUtil.deleteData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, deleteTriple);
        UpdateRequest delete = UpdateFactory.create(sparqlDelete);
        processor = UpdateExecutionFactory.createRemote(delete, conn.updateEndpoint);
        processor.execute();

        String updateTriple = String.format("<%s> <%s> \"%s\" .", subject, predicate, object);
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, updateTriple);
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();
    }

    public List<String> executeSparqlQuery(String sparqlQuery) {

        System.out.println(sparqlQuery);

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        String varName;
        RDFNode varValue;
        List<String> values = new ArrayList<>();

        while (results.hasNext()) {

            // A single answer from a SELECT query
            QuerySolution querySolution = results.next();
            Iterator<String> variableBindings = querySolution.varNames();

            // Retrieve variable bindings
            while (variableBindings.hasNext()) {

                varName = variableBindings.next();
                varValue = querySolution.get(varName);

                System.out.println(varName + ": " + varValue);
                values.add(varValue.toString().replace(namespace, ""));
            }
            System.out.println();
        }

        query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        System.out.println("[INFO] Showing the results for SPARQL query in native SPARQL XML format.\n");
        results = query.execSelect();
        ResultSetFormatter.out(System.out, results);


        query.close();
        System.out.println("[INFO] End.");
        return values;
    }
}
