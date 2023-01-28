package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.Resenje;
import com.zavod.model.resenje.TInformacijeOZahtevu;
import com.zavod.model.resenje.TOdluka;
import com.zavod.model.resenje.TSluzbenik;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
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
        this.zahtevRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }

    public List<Zahtev> search(List<String> query) {
        return zahtevRepository.search(query);
    }
}
