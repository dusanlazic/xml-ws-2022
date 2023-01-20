package com.zavod.service;

import com.zavod.model.Zahtev;
import com.zavod.repository.AutorskaRepository;
import com.zavod.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.List;

@Service
public class AutorskaService {

    @Autowired
    public AutorskaRepository autorskaRepository;

    @Autowired
    public MetadataRepository metadataRepository;

    public List<Zahtev> getAll() {
        return autorskaRepository.getAll();
    }

    public Zahtev getZahtev(String id) throws XMLDBException {
        return autorskaRepository.findById(id);
    }

    public void addZahtev(Zahtev zahtev) {
        this.autorskaRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }

    public List<Zahtev> search(List<String> query) {
        return autorskaRepository.search(query);
    }

    public void metaSearch() {
        String graphName = "<http://localhost:8080/fuseki-autorska/ZahteviDataset/data/zahtevi/metadata>";
        String query = "SELECT ?subject FROM " + graphName +
                "WHERE {\n" +
                "  ?subject ?predicate ?object\n" +
                "}\n" +
                "LIMIT 25";
        metadataRepository.executeSparqlQuery(query);
    }
}
