package com.zavod.repository;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import lombok.var;
import org.springframework.stereotype.Repository;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

@Repository
public class MetadataRepository {

    private static final String XSLT_FILE = "src/main/resources/xslt/zahtev-meta.xsl";

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

}
