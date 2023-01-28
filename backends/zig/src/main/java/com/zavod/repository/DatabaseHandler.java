package com.zavod.repository;

import com.zavod.util.AuthenticationUtilities;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class DatabaseHandler {
    private final String dataPath;
    private final String collectionId;

    private static AuthenticationUtilities.ExistConnectionProperties conn;

    public DatabaseHandler(String dataPath, String collectionId) {
        this.dataPath = dataPath;
        this.collectionId = collectionId;
    }

    public String getDataPath() {
        return dataPath;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void establishConnection() {
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

    public Collection getOrCreateCollection() throws XMLDBException {
        return getOrCreateCollection(collectionId, 0);
    }

    public Collection getCollection() throws XMLDBException {
        return DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
    }

    private Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
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
