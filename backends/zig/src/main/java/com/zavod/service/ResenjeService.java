package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.Resenje;
import com.zavod.model.resenje.TInformacijeOZahtevu;
import com.zavod.model.resenje.TOdluka;
import com.zavod.model.resenje.TSluzbenik;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.ZahtevRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;

import static com.zavod.util.ServiceUtil.brojToXml;
import static com.zavod.util.ServiceUtil.today;

@Service
public class ResenjeService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    public void addResenje(ResenjeDTO resenjeDTO, String brojPrijave, Authentication authentication) throws XMLDBException, DatatypeConfigurationException {
        KorisnikDTO korisnikDTO = (KorisnikDTO) authentication.getPrincipal();
        Zahtev zahtev = zahtevRepository.findById(brojPrijave);

        Resenje resenje = new Resenje(
                new TSluzbenik(korisnikDTO.getIme(), korisnikDTO.getPrezime()),
                new TInformacijeOZahtevu(brojToXml(brojPrijave)),
                new TOdluka(today(), resenjeDTO.getOdluka().getObrazlozenje(), resenjeDTO.getOdluka().isPrihvacen())
        );
    }

    public Resenje getResenje(String brojPrijave) {
        throw new NotImplementedException();
    }
}
