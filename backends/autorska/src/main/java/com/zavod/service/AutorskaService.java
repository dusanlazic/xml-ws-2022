package com.zavod.service;

import com.zavod.model.Zahtev;
import com.zavod.repository.AutorskaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorskaService {

    @Autowired
    public AutorskaRepository autorskaRepository;

    public List<Zahtev> getAll() {
        return autorskaRepository.getAll();
    }

    public Zahtev getZahtev(String id) {
        return autorskaRepository.getAll()
                .stream().filter(x -> x.getInformacijeZavoda().getBrojPrijave().equals(id))
                .findFirst().orElse(null);
    }

    public void addZahtev(Zahtev zahtev) {
        this.autorskaRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }
}
