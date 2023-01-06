package com.zavod.service;

import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import com.zavod.repository.ZigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZigService {

    @Autowired
    public ZigRepository zigRepository;

    public Zahtevi getAll() {
        return zigRepository.getAll();
    }

    public void addZahtev(TZahtev zahtev) {
        this.zigRepository.getAll().getZahtev().add(zahtev);
    }
}
