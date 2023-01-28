package com.zavod.service;

import com.zavod.model.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.List;

@Service
public class ZigService {

    @Autowired
    public ZigRepository zigRepository;

    @Autowired
    public MetadataRepository metadataRepository;

    public List<Zahtev> getAll() {
        return zigRepository.getAll();
    }

    public Zahtev getZahtev(String id) throws XMLDBException {
        return zigRepository.findById(id);
    }

    public void addZahtev(Zahtev zahtev) {
        this.zigRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }

    public List<Zahtev> search(List<String> query) {
        return zigRepository.search(query);
    }
}
