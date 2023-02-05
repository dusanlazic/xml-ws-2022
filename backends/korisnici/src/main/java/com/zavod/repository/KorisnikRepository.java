package com.zavod.repository;

import com.zavod.model.Korisnik;
import org.springframework.stereotype.Repository;


@Repository
public class KorisnikRepository extends ExistRepository<Korisnik>{

    public KorisnikRepository() {
        super(Korisnik.class);
    }

    public boolean existsByEmail(String email) {
        return super.getAll().stream().anyMatch(k -> k.getEmail().equals(email));
    }
}
