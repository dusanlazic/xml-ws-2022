package com.zavod.controller;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.zavod.api.ResponseOk;
import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.Resenje;
import com.zavod.model.resenje.TInformacijeOZahtevu;
import com.zavod.model.resenje.TOdluka;
import com.zavod.model.resenje.TSluzbenik;
import com.zavod.service.MailingService;
import com.zavod.service.PDFService;
import com.zavod.service.ResenjeService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/resenja")
public class ResenjeController {

    @Autowired
    private ResenjeService resenjeService;

    @Autowired
    private MailingService mailingService;

    @Autowired
    private PDFService pdfService;

    @PostMapping(path = "/{brojPrijave}", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public ResponseOk addResenje(@PathVariable String brojPrijave, @RequestBody ResenjeDTO resenje, Authentication authentication) throws XMLDBException, DatatypeConfigurationException, IOException {
        Resenje createdResenje = resenjeService.addResenje(resenje, brojPrijave, authentication);
        resenjeService.sendResenjeToUser(createdResenje);
        return new ResponseOk("Resenje podneto.");
    }

    @GetMapping(path = "/{brojPrijave}", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public Resenje getResenje(@PathVariable String brojPrijave) throws XMLDBException {
        System.out.println("GET RES " + brojPrijave);
        return resenjeService.getResenje(brojPrijave);
    }

    @GetMapping(path = "/mejl")
    public void sendMejl() throws XMLDBException {
        KorisnikDTO korisnik = new KorisnikDTO();
        korisnik.setEmail("ubreteam2023@gmail.com");
        korisnik.setIme("Ubrat");
        korisnik.setPrezime("Team");
        Resenje resenje = new Resenje();
        resenje.setZahtev(new TInformacijeOZahtevu());
        resenje.setOdluka(new TOdluka());
        resenje.getZahtev().setBrojPrijave("123");
        resenje.getOdluka().setDatum(XMLGregorianCalendarImpl.createDate(2020, 12, 12, DatatypeConstants.FIELD_UNDEFINED));
        resenje.getOdluka().setPrihvacen(false);
        resenje.getOdluka().setObrazlozenje("Ne valja ti zahtev lmaoooo!");
        resenje.setSluzbenik(new TSluzbenik());
        resenje.getSluzbenik().setIme("Ubratee");
        resenje.getSluzbenik().setPrezime("Teameee");
        mailingService.sendResenjeMail(korisnik, resenje, new File("gen/output/resenje-A-0000-01.pdf"));
    }

    @GetMapping(path = "/export")
    public void exportResenje() throws XMLDBException {
        Resenje resenje = resenjeService.getResenje("A-0000-01");
        pdfService.exportToResource(resenje, MediaType.APPLICATION_PDF);
    }
}
