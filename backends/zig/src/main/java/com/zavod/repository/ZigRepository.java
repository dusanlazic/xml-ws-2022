package com.zavod.repository;

import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import org.springframework.stereotype.Repository;


@Repository
public interface ZigRepository {
    public Zahtevi getAll();

    public void saveAll(Zahtevi zahtevi);

    public void load();

    public void addZahtev(TZahtev zahtev);
}
