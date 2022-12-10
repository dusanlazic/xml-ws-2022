package com.zavod.repository;

import com.zavod.model.autorska.TZahtev;
import com.zavod.model.autorska.Zahtevi;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorskaRepository {
    public Zahtevi getAll();

    public void saveAll(Zahtevi zahtevi);

    public void load();

    public void addZahtev(TZahtev zahtev);
}
