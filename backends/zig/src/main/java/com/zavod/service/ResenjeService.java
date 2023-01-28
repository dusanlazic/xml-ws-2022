package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.Resenje;
import com.zavod.model.resenje.TInformacijeOZahtevu;
import com.zavod.model.resenje.TOdluka;
import com.zavod.model.resenje.TSluzbenik;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.ResenjeRepository;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;

import static com.zavod.util.ServiceUtil.*;

@Service
public class ResenjeService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private ResenjeRepository resenjeRepository;

    public void addResenje(ResenjeDTO resenjeDTO, String brojPrijave, Authentication authentication) throws XMLDBException, DatatypeConfigurationException {
        KorisnikDTO korisnikDTO = (KorisnikDTO) authentication.getPrincipal();
        Zahtev zahtev = zahtevRepository.findById(brojPrijave);

        Resenje resenje = new Resenje(
                new TSluzbenik(korisnikDTO.getIme(), korisnikDTO.getPrezime()),
                new TInformacijeOZahtevu(brojPrijave),
                new TOdluka(today(), resenjeDTO.getOdluka().getObrazlozenje(), resenjeDTO.getOdluka().isPrihvacen())
        );

        resenjeRepository.save(resenje, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
    }

    public Resenje getResenje(String brojPrijave) throws XMLDBException {
        return resenjeRepository.findById(brojPrijave);
    }
}
