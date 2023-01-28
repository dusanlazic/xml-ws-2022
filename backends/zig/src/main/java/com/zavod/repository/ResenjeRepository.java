package com.zavod.repository;

import com.zavod.model.resenje.Resenje;
import org.springframework.stereotype.Repository;


@Repository
public class ResenjeRepository extends ExistRepository<Resenje>{

    public ResenjeRepository() {
        super(
                Resenje.class,
                new DatabaseHandler(
                        "./src/main/resources/xml/resenja/",
                        "/db/resenja"
                )
        );
    }
}
