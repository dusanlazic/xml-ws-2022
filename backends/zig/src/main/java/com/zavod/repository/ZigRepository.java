package com.zavod.repository;

import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import org.springframework.stereotype.Repository;


@Repository
public class ZigRepository extends ExistRepository<Zahtevi, TZahtev>{

    public ZigRepository() {
        super(Zahtevi.class, TZahtev.class);
    }
}
