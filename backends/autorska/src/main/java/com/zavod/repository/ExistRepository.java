package com.zavod.repository;

import com.zavod.util.AuthenticationUtilities;
import com.zavod.util.MarshallingService;
import lombok.var;
import org.xmldb.api.modules.XMLResource;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ExistRepository<T> {


    MarshallingService<T> marshallingService;
    static AuthenticationUtilities.ExistConnectionProperties conn = AuthenticationUtilities.loadExistProperties();;

    public ExistRepository(Class<T> tt) {
        this.marshallingService = new MarshallingService<>(tt);
        DatabaseHandler.establishConnection();
    }

    public XMLResource getResource(String documentName) {
        try {
            var col = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            var res = (XMLResource) col.getResource(documentName);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<XMLResource> getResources() {
        try {
            var col = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            var resources = col.listResources();
            return Arrays.stream(resources).map(this::getResource).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getAll() {
        try {
            return this.getResources()
                        .stream()
                        .map(this::readResource)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private T readResource(XMLResource resource) {
        try {
            var xml = resource.getContent().toString();
            return marshallingService.unmarshall(resource.getContentAsDOM());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(T t, String docName) {
        try {
            var col = DatabaseHandler.getOrCreateCollection(DatabaseHandler.collectionId);
            var res = (XMLResource) col.createResource(docName, XMLResource.RESOURCE_TYPE);
            OutputStream os = new ByteArrayOutputStream();
            marshallingService.marshall(t, os);
            res.setContent(os);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAll(List<T> documents, List<String> docNames) {
        for (int i = 0; i < documents.size(); i++) {
            save(documents.get(i), docNames.get(i));
        }
    }

    public File[] getAllXmlFiles() {
        File dir = new File(DatabaseHandler.dataPath);
        return dir.listFiles((dir1, name) -> name.endsWith(".xml"));
    }

    public void load() {
        File[] files = getAllXmlFiles();
        List<T> documents = new ArrayList<>();
        List<String> docNames = new ArrayList<>();
        for (File file : files) {
            try {
                FileInputStream fs = new FileInputStream(file);
                documents.add(marshallingService.unmarshall(fs));
                docNames.add(file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        saveAll(documents, docNames);
    }

}
