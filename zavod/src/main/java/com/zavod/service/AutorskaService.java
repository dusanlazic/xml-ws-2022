package com.zavod.service;

import com.zavod.model.autorska.TZahtev;
import com.zavod.model.autorska.Zahtevi;
import com.zavod.repository.AutorskaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorskaService {

    @Autowired
    public AutorskaRepository autorskaRepository;

    public Zahtevi getAll() {
        return autorskaRepository.getAll();
    }

    public void addZahtev(TZahtev zahtev) {
        this.autorskaRepository.getAll().getZahtev().add(zahtev);
    }
}
