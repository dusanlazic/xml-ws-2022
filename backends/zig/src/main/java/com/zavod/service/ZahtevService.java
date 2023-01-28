package com.zavod.service;

import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    public ZahtevRepository zahtevRepository;

    @Autowired
    public MetadataRepository metadataRepository;

    public List<Zahtev> getAll() {
        return zahtevRepository.getAll();
    }

    public Zahtev getZahtev(String id) throws XMLDBException {
        return zahtevRepository.findById(id);
    }

    public void addZahtev(Zahtev zahtev) {
        String id = createId();
        System.out.println("Assigned id to new request: " + id);
        zahtev.getInformacijeZavoda().setBrojPrijave(id);
        this.zahtevRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }

    private String createId() {
        try {
            long maximum = 0;
            ResourceSet resourceSet = zahtevRepository.executeXPath("//Zahtev/Informacije_Zavoda/broj_prijave/text()");
            ResourceIterator iterator = resourceSet.getIterator();
            while (iterator.hasMoreResources()) {
                String id = iterator.nextResource().getContent().toString();
                long ordinalPart = Long.parseLong(id.split("-")[1]);
                long yearPart = Long.parseLong(id.split("-")[2]);
                if (ordinalPart > maximum && yearPart == LocalDate.now().getYear() % 100) {
                    maximum = ordinalPart;
                }
            }
            return "Z-" + (maximum + 1) + "-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        } catch (XMLDBException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public List<Zahtev> search(List<String> query) {
        return zahtevRepository.search(query);
    }
}
