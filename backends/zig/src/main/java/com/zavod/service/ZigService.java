package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.Resenje;
import com.zavod.model.resenje.TInformacijeOZahtevu;
import com.zavod.model.resenje.TOdluka;
import com.zavod.model.resenje.TSluzbenik;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZigRepository;
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
public class ZigService {

    @Autowired
    public ZigRepository zigRepository;

    @Autowired
    public MetadataRepository metadataRepository;

    public List<Zahtev> getAll() {
        return zigRepository.getAll();
    }

    public Zahtev getZahtev(String id) throws XMLDBException {
        return zigRepository.findById(id);
    }

    public void addZahtev(Zahtev zahtev) {
        this.zigRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }

    public List<Zahtev> search(List<String> query) {
        return zigRepository.search(query);
    }

    public Resenje addResenje(ResenjeDTO resenjeDTO, String brojPrijave, Authentication authentication) throws XMLDBException, DatatypeConfigurationException {
        KorisnikDTO korisnikDTO = (KorisnikDTO) authentication.getPrincipal();
        Zahtev zahtev = getZahtev(brojPrijave);

        return new Resenje(
                new TSluzbenik(korisnikDTO.getIme(), korisnikDTO.getPrezime()),
                new TInformacijeOZahtevu(brojToXml(brojPrijave)),
                new TOdluka(today(), resenjeDTO.getOdluka().getObrazlozenje(), resenjeDTO.getOdluka().isPrihvacen())
        );
    }

    public static String brojToUrl(String brojPrijave) {
        return brojPrijave
                .replace("Ж","Z")
                .replace("/", "-");
    }

    public static String brojToXml(String brojPrijave) {
        String retVal = brojPrijave.replace("Z","Ж");
        int idx = retVal.lastIndexOf("-");
        return retVal.substring(0, idx) + "/" + retVal.substring(idx + 1);
    }
    
    private XMLGregorianCalendar today() throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar xmlDate = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

        xmlDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        xmlDate.setTime(DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED);

        return xmlDate;
    }
}
