package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.Resenje;
import com.zavod.model.resenje.TInformacijeOZahtevu;
import com.zavod.model.resenje.TOdluka;
import com.zavod.model.resenje.TSluzbenik;
import com.zavod.model.zahtev.TFizickoLice;
import com.zavod.model.zahtev.TLice;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.ResenjeRepository;
import com.zavod.repository.ZahtevRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.Response;

import java.io.IOException;

import static com.zavod.util.ServiceUtil.*;
import static com.zavod.util.ServiceUtil.today;

@Service
public class ResenjeService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private ResenjeRepository resenjeRepository;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private MailingService mailingService;


    public Resenje addResenje(ResenjeDTO resenjeDTO, String brojPrijave, Authentication authentication) throws XMLDBException, DatatypeConfigurationException {
        KorisnikDTO korisnikDTO = (KorisnikDTO) authentication.getPrincipal();
        Zahtev zahtev = zahtevRepository.findById(brojPrijave);

        Resenje resenje = new Resenje(
                new TSluzbenik(korisnikDTO.getIme(), korisnikDTO.getPrezime()),
                new TInformacijeOZahtevu(brojPrijave),
                new TOdluka(today(), resenjeDTO.getOdluka().getObrazlozenje(), resenjeDTO.getOdluka().isPrihvacen())
        );

        resenjeRepository.save(resenje, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
        return resenje;
    }

    public Resenje getResenje(String brojPrijave) throws XMLDBException {
        return resenjeRepository.findById(brojPrijave);
    }

    public void sendResenjeToUser(Resenje resenje) throws XMLDBException, IOException {
        ResponseEntity<Resource> response = pdfService.exportToResource(resenje, MediaType.APPLICATION_PDF);
        Zahtev zahtev = zahtevRepository.findById(resenje.getZahtev().getBrojPrijave());
        TLice podnosilac = zahtev.getPodnosilac();
        String prezime = "";
        if (podnosilac instanceof TFizickoLice) {
            prezime = ((TFizickoLice) podnosilac).getPrezime();
        }
        KorisnikDTO korisnikDTO = new KorisnikDTO(0L, podnosilac.getIme(), prezime, zahtev.getInformacijeSistema().getEmail(), "gradjanin");
        System.out.println("dodjem dovde");
        System.out.println(response.getBody());
        mailingService.sendResenjeMail(korisnikDTO, resenje, response.getBody().getFile());
    }
}
