package com.zavod.repository;

import com.zavod.model.Zahtev;
import com.zavod.util.AuthenticationUtilities;
import com.zavod.util.MarshallingService;
import lombok.var;
import org.exist.xmldb.EXistResource;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.zavod.util.XUpdateTemplate.TARGET_NAMESPACE;

public abstract class ExistRepository<T> {


    MarshallingService<T> marshallingService;
    static AuthenticationUtilities.ExistConnectionProperties conn = AuthenticationUtilities.loadExistProperties();;
    private Collection col;
    private XMLResource res;

    public ExistRepository(Class<T> tt) {
        this.marshallingService = new MarshallingService<>(tt);
        DatabaseHandler.establishConnection();
    }

    public XMLResource getResource(String documentName) {
        col = null;
        res = null;
        try {
            col = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            res = (XMLResource) col.getResource(documentName);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup(col, res);
        }
        return null;
    }

    public T findById(String id) throws XMLDBException {
        return marshallingService.unmarshall(this.getResource(id + ".xml").getContentAsDOM());
    }

    public List<T> findByIds(List<String> ids) throws XMLDBException {
        List<T> res = new ArrayList();
        for(String id : ids) {
            res.add(findById(id));
        }
        return res;
    }

    public List<XMLResource> getResources() {
        col = null;
        res = null;
        try {
            col = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            var resources = col.listResources();
            return Arrays.stream(resources).map(this::getResource).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            cleanup(col, res);
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
        } finally {
            cleanup(col, res);
        }
    }

    private T readResource(XMLResource resource) {
        col = null;
        res = null;
        try {
            var xml = resource.getContent().toString();
            return marshallingService.unmarshall(resource.getContentAsDOM());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup(col, res);
        }
        return null;
    }

    public void save(T t, String docName) {
        col = null;
        res = null;
        try {
            col = DatabaseHandler.getOrCreateCollection(DatabaseHandler.collectionId);
            res = (XMLResource) col.createResource(docName, XMLResource.RESOURCE_TYPE);
            OutputStream os = new ByteArrayOutputStream();
            marshallingService.marshall(t, os);
            res.setContent(os);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup(col, res);
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
            } finally {
                cleanup(col, res);
            }
        }
        saveAll(documents, docNames);
    }

    public List<T> search(List<String> queries) {
        org.xmldb.api.base.Collection col = null;
        Resource res = null;
        try {
            col = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            xpathService.setNamespace("", TARGET_NAMESPACE);

            String xpathExp = this.createQueryString(queries);
            System.out.println("XPath expression: " + xpathExp);
            ResourceSet result = xpathService.query(xpathExp);
            ResourceIterator i = result.getIterator();

            List<T> results = new ArrayList<>();
            while (i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    results.add(marshallingService.unmarshall(((XMLResource)res).getContentAsDOM()));
                } finally {
                    cleanup(null, res);
                }
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanup(col, res);
        }
        return null;
    }

    public String createQueryString(List<String> queries) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queries.size(); i++) {
            if (i == 0) {
                sb.append("/*[contains(lower-case(.), '").append(queries.get(i).toLowerCase());
            } else {
                sb.append("') and contains(lower-case(.), '").append(queries.get(i).toLowerCase());
            }
            if(i == queries.size() - 1) {
                sb.append("')]");
            }
        }
        return sb.toString();
    }

    public void cleanup(Collection col, Resource res) {
        if(res != null) {
            try {
                ((EXistResource)res).freeResources();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }

        if(col != null) {
            try {
                col.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }

    }

}
