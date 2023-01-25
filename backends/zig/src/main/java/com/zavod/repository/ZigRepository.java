package com.zavod.repository;

import com.zavod.model.Zahtev;
import org.springframework.stereotype.Repository;


@Repository
public class ZigRepository extends ExistRepository<Zahtev>{

    public ZigRepository() {
        super(Zahtev.class);
    }
}
