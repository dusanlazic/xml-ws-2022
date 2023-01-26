package com.zavod.service;

import com.zavod.model.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.KorisniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.List;

@Service
public class KorisniciService {

    @Autowired
    public KorisniciRepository zigRepository;

    public List<Zahtev> getAll() {
        return zigRepository.getAll();
    }

    public Zahtev getKorisnik(String id) throws XMLDBException {
        return zigRepository.findById(id);
    }

    public void addKorisnik(Zahtev zahtev) {
        this.zigRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }

    public List<Zahtev> search(List<String> query) {
        return zigRepository.search(query);
    }


}
