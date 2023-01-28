package com.zavod.repository;

import com.zavod.model.zahtev.Zahtev;
import org.springframework.stereotype.Repository;


@Repository
public class ZahtevRepository extends ExistRepository<Zahtev>{

    public ZahtevRepository() {
        super(
                Zahtev.class,
                new DatabaseHandler(
                        "./src/main/resources/xml/zahtevi/",
                        "/db/zahtevi"
                )
        );
    }
}
