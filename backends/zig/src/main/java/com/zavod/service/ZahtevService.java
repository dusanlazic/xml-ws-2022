package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.model.resenje.StatusResenja;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    public void addZahtev(Zahtev zahtev, KorisnikDTO korisnik) throws FileNotFoundException, TransformerException {
        String id = createId();
        zahtev.getInformacijeZavoda().setBrojPrijave(id);
        zahtev.getInformacijeZavoda().setStatusResenja(StatusResenja.NA_CEKANJU.toString());
        zahtev.getInformacijeSistema().setEmail(korisnik.getEmail());
        this.zahtevRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
        String rdf = this.metadataRepository.loadRdf(zahtev);
        this.metadataRepository.writeRdf(rdf);
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


    public List<Zahtev> search(List<String> query, boolean showOnlyPrihvaceni) {
        if (showOnlyPrihvaceni)
            return zahtevRepository.search(query).stream()
                    .filter(z -> z.getInformacijeZavoda().getStatusResenja().equals(StatusResenja.PRIHVACEN.toString()))
                    .collect(Collectors.toList());
        return zahtevRepository.search(query);
    }
}
