package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.dto.SearchRequest;
import com.zavod.dto.Zahtevi;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.service.MetadataService;
import com.zavod.service.PDFService;
import com.zavod.service.ZahtevService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

@RestController
@RequestMapping("/zahtevi")
public class ZahtevController {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private PDFService pdfService;

    @PostMapping(path = "/", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('GRADJANIN')")
    public ResponseOk create(@RequestBody Zahtev zahtev) {
        zahtevService.addZahtev(zahtev);
        return new ResponseOk("Zahtev kreiran.");
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public Zahtevi getAll() {
        return new Zahtevi(zahtevService.getAll());
    }

    @GetMapping(path = "/qr/{brojPrijave}.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> qr(@PathVariable String brojPrijave) throws Exception {
        return pdfService.qrCodeToResource(brojPrijave);
    }

    @GetMapping(path = "/pdf/{brojPrijave}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> pdf(@PathVariable String brojPrijave) throws XMLDBException {
        return pdfService.exportToResource(zahtevService.getZahtev(brojPrijave), MediaType.APPLICATION_PDF);
    }

    @GetMapping(path = "/xhtml/{brojPrijave}.xhtml", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<Resource> xhtml(@PathVariable String brojPrijave) throws XMLDBException {
        return pdfService.exportToResource(zahtevService.getZahtev(brojPrijave), MediaType.APPLICATION_XHTML_XML);
    }

    @GetMapping(path = "/rdf/{brojPrijave}.rdf", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Resource> rdf(@PathVariable String brojPrijave) {
        throw new NotImplementedException();
    }

    @GetMapping(path = "/json/{brojPrijave}.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> json(@PathVariable String brojPrijave) {
        throw new NotImplementedException();
    }

    @PostMapping(path = "/search", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public Zahtevi search(@RequestBody SearchRequest searchRequest) {
        if(searchRequest.getQuery().size() == 0) return new Zahtevi();
        return new Zahtevi(zahtevService.search(searchRequest.getQuery()));
    }

    @PostMapping(path = "/search-meta", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public Zahtevi metaSearch(@RequestBody MetaSearchRequest metaSearchRequest) throws XMLDBException {
        for (MetaSearchQuery searchQuery: metaSearchRequest.getQuery()) {
            System.out.println(searchQuery.getPredicate() + " " + searchQuery.getObject() + " " + searchQuery.getOperator());
        }
        return new Zahtevi(metadataService.metaSearch(metaSearchRequest));
    }

    @GetMapping("/export/rdf")
    public void exportToRDF() throws XMLDBException {
        String brojPrijave = "Ж-0/10";
        metadataService.exportToRDF(brojPrijave);
    }

    @GetMapping("/export/json")
    public void exportToJSON() throws XMLDBException {
        String brojPrijave = "Ж-0/10";
        metadataService.exportToJSON(brojPrijave);
    }

//    @GetMapping(path = "/{brojPrijave}_resenje.pdf", produces = MediaType.APPLICATION_PDF_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
//    @PreAuthorize("hasAnyAuthority('SLUZBENIK', 'GRADJANIN')")
//    public ResponseEntity<Resource> getResenje(@PathVariable String brojPrijave, Authentication authentication) {
//        throw new NotImplementedException();
//    }

    @GetMapping(path = "/izvestaj_{startDate}_{endDate}.pdf", produces = MediaType.APPLICATION_PDF_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAuthority('SLUZBENIK')")
    public Object exportIzvestaj(@PathVariable String startDate, @PathVariable String endDate) {
        throw new NotImplementedException();
    }
}
