package com.zavod.repository;

import com.zavod.model.Zahtev;
import org.springframework.stereotype.Repository;


@Repository
public class AutorskaRepository extends ExistRepository<Zahtev>{

    public AutorskaRepository() {
        super(Zahtev.class);
    }
}
