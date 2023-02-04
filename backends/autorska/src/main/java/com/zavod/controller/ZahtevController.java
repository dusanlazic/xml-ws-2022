package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.*;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.service.IzvestajService;
import com.zavod.service.MetadataService;
import com.zavod.service.PDFService;
import com.zavod.service.ZahtevService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/zahtevi")
public class ZahtevController {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private IzvestajService izvestajService;

    @Autowired
    private PDFService pdfService;

    @PostMapping(path = "/", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN')")
    public ResponseOk create(@RequestBody Zahtev zahtev, Authentication authentication) throws FileNotFoundException, TransformerException, DatatypeConfigurationException {
        KorisnikDTO korisnik = (KorisnikDTO) authentication.getPrincipal();
        zahtevService.addZahtev(zahtev, korisnik);
        return new ResponseOk("Zahtev kreiran.");
    }

    @GetMapping(path = "/{brojPrijave}", produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtev getZahtev(@PathVariable String brojPrijave) throws Exception {
        System.out.println("ZahtevController.getZahtev " + brojPrijave);
        return zahtevService.getZahtev(brojPrijave);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public Zahtevi getAll() {
        return new Zahtevi(zahtevService.getAll());
    }

    @GetMapping(path = "/export/{brojPrijave}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> exportPdf(@PathVariable String brojPrijave) throws XMLDBException {
        return pdfService.exportToResource(zahtevService.getZahtev(brojPrijave), MediaType.APPLICATION_PDF);
    }

    @GetMapping(path = "/export/{brojPrijave}.xhtml", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<Resource> exportXhtml(@PathVariable String brojPrijave) throws XMLDBException {
        return pdfService.exportToResource(zahtevService.getZahtev(brojPrijave), MediaType.APPLICATION_XHTML_XML);
    }

    @GetMapping(path = "/export/{brojPrijave}.rdf", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportRdf(@PathVariable String brojPrijave) {
        return metadataService.exportToRDF(brojPrijave);
    }

    @GetMapping(path = "/export/{brojPrijave}.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> exportJson(@PathVariable String brojPrijave) {
        return metadataService.exportToJSON(brojPrijave);
    }

    @PostMapping(path = "/search", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('SLUZBENIK', 'GRADJANIN')")
    public Zahtevi search(@RequestBody SearchRequest searchRequest, Authentication authentication) {
        KorisnikDTO korisnik = (KorisnikDTO) authentication.getPrincipal();
        boolean hideNeprihvaceni = korisnik.getUloga().equals("gradjanin");

        if(searchRequest.getQuery().size() == 0) return new Zahtevi();
        return new Zahtevi(zahtevService.search(searchRequest.getQuery(), hideNeprihvaceni));
    }

    @PostMapping(path = "/search-meta", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('SLUZBENIK', 'GRADJANIN')")
    public Zahtevi metaSearch(@RequestBody MetaSearchRequest metaSearchRequest, Authentication authentication) throws XMLDBException {
        KorisnikDTO korisnik = (KorisnikDTO) authentication.getPrincipal();
        boolean hideNeprihvaceni = korisnik.getUloga().equals("gradjanin");

        for (MetaSearchQuery searchQuery: metaSearchRequest.getQuery()) {
            System.out.println(searchQuery.getPredicate() + " " + searchQuery.getObject() + " " + searchQuery.getOperator());
        }
        return new Zahtevi(metadataService.metaSearch(metaSearchRequest, hideNeprihvaceni));
    }

    @GetMapping(path = "/my", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('SLUZBENIK', 'GRADJANIN')")
    public Zahtevi myReqs(Authentication authentication) throws XMLDBException {
        KorisnikDTO korisnik = (KorisnikDTO) authentication.getPrincipal();
        MetaSearchRequest metaSearchRequest = new MetaSearchRequest();
        metaSearchRequest.setQuery(new ArrayList<>());
        MetaSearchQuery query = new MetaSearchQuery();
        query.setPredicate("Nalog");
        query.setObject(korisnik.getEmail());
        query.setRelation("=");
        query.setOperator("I");
        metaSearchRequest.getQuery().add(query);
        for (MetaSearchQuery searchQuery: metaSearchRequest.getQuery()) {
            System.out.println(searchQuery.getPredicate() + " " + searchQuery.getObject() + " " + searchQuery.getOperator());
        }
        return new Zahtevi(metadataService.metaSearch(metaSearchRequest, false));
    }


    @GetMapping(path = "/nereseni", produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtevi getNereseni(Authentication authentication) throws XMLDBException {
        KorisnikDTO korisnik = (KorisnikDTO) authentication.getPrincipal();
        if (korisnik.getUloga().equals("gradjanin")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nemate pravo pristupa");
        }
        return new Zahtevi(zahtevService.getUnresolved());
    }

    @GetMapping(path = "/izvestaj", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> exportIzvestaj(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                   @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) throws DatatypeConfigurationException, XMLDBException {
        return pdfService.exportToResource(izvestajService.generateNew(startDate, endDate));
    }
}
