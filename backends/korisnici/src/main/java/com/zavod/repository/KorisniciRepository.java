package com.zavod.repository;

import com.zavod.model.Korisnik;
import org.springframework.stereotype.Repository;


@Repository
public class KorisniciRepository extends ExistRepository<Korisnik>{

    public KorisniciRepository() {
        super(Korisnik.class);
    }
}
