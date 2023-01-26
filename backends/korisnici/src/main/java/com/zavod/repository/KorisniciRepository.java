package com.zavod.repository;

import com.zavod.model.Zahtev;
import org.springframework.stereotype.Repository;


@Repository
public class KorisniciRepository extends ExistRepository<Zahtev>{

    public KorisniciRepository() {
        super(Zahtev.class);
    }
}
