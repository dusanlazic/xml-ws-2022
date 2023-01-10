package com.zavod.repository;

import com.zavod.util.MarshallingService;
import com.zavod.util.XUpdateUtil;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XUpdateQueryService;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public abstract class ExistRepository<C, T> {

    MarshallingService<C> collectionMarshallingService;

    MarshallingService<T> typeMarshallingService;

    public ExistRepository(Class<C> ct, Class<T> tt) {
        this.typeMarshallingService = new MarshallingService<>(tt);
        this.collectionMarshallingService = new MarshallingService<>(ct);
    }

    public C getAll() {
        try {
            Collection collection = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            XMLResource resource = DatabaseHandler.getResource();
            C res = collectionMarshallingService.unmarshall(resource.getContentAsDOM());
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAll(C c) {
        try {
            DatabaseHandler.establishConnection();
            XMLResource resource = DatabaseHandler.createResource();
            Collection collection = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            OutputStream os = new ByteArrayOutputStream();
            collectionMarshallingService.marshall(c, os);
            resource.setContent(os);
            collection.storeResource(resource);
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {
        try {
            DatabaseHandler.establishConnection();
            XMLResource resource = DatabaseHandler.createResource();
            C c = collectionMarshallingService.unmarshall(new FileInputStream(DatabaseHandler.dataPath));
            Collection collection = DatabaseHandler.getCollection(DatabaseHandler.collectionId);
            OutputStream os = new ByteArrayOutputStream();
            collectionMarshallingService.marshall(c, os);
            resource.setContent(os);
            collection.storeResource(resource);
            System.out.println("Finished loading.");
        } catch (XMLDBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(T t) {
        try {
            XUpdateQueryService xupdateService = DatabaseHandler.getUpdateService();
            OutputStream os = new ByteArrayOutputStream();
            typeMarshallingService.marshall(t, os);
            String marshalled = XUpdateUtil.clipString(os.toString());
            String appendString = XUpdateUtil.createAppendString(DatabaseHandler.collectionName, marshalled);
            long mods = xupdateService.updateResource(DatabaseHandler.documentId, appendString);
            System.out.println("[INFO] " + mods + " modifications processed.");
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
    }
}
