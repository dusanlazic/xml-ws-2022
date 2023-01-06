package com.zavod.repository;

import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import org.springframework.stereotype.Repository;


@Repository
public class AutorskaRepository extends ExistRepository<Zahtevi, TZahtev>{

    public AutorskaRepository() {
        super(Zahtevi.class, TZahtev.class);
    }
}
