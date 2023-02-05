package com.zavod.repository;

import com.zavod.util.AuthenticationUtilities;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XUpdateQueryService;

import java.io.IOException;

public class DatabaseHandler {
    public static String contextPath = "com.zavod.model";
    public static String dataPath = "./src/main/resources/xml/";
    public static String documentId = "k1.xml";
    public static String collectionId = "/db/korisnici";

    public static String collectionName = "/Korisnici";

    private static AuthenticationUtilities.ExistConnectionProperties conn;

    public static void establishConnection() {
        try {
            conn = AuthenticationUtilities.loadExistProperties();
            Class<?> cl = Class.forName(conn.driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException e) {
            System.out.println("Database fatal error!");
        }
    }

    public static XUpdateQueryService getUpdateService() throws XMLDBException {
        Collection col = getCollection(collectionId);
        XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
        xupdateService.setProperty("indent", "yes");
        return xupdateService;
    }

    public static XMLResource createResource() throws XMLDBException {
        Collection col = getOrCreateCollection(collectionId);;
        return (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
    }

    public static XMLResource getResource() throws XMLDBException {
        Collection col = getOrCreateCollection(collectionId);;
        return (XMLResource) col.getResource(documentId);
    }

    public static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }

    public static Collection getCollection(String collectionUri) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        return col;
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

        // create the collection if it does not exist
        if (col != null) {
            return col;
        }

        if (collectionUri.startsWith("/")) {
            collectionUri = collectionUri.substring(1);
        }

        String[] pathSegments = collectionUri.split("/");

        if(pathSegments.length == 0) {
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        }

        StringBuilder path = new StringBuilder();

        for(int i = 0; i <= pathSegmentOffset; i++) {
            path.append("/").append(pathSegments[i]);
        }

        Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

        if (startCol == null) {

            // child collection does not exist

            String parentPath = path.substring(0, path.lastIndexOf("/"));
            Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

            CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

            System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
            col = mgt.createCollection(pathSegments[pathSegmentOffset]);

            col.close();
            parentCol.close();

        } else {
            startCol.close();
        }
        return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
    }
}
