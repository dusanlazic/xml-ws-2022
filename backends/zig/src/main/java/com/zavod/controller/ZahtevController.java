package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.dto.SearchRequest;
import com.zavod.dto.Zahtevi;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.service.IzvestajService;
import com.zavod.service.MetadataService;
import com.zavod.service.PDFService;
import com.zavod.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;
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
    public ResponseOk create(@RequestBody Zahtev zahtev) {
        zahtevService.addZahtev(zahtev);
        return new ResponseOk("Zahtev kreiran.");
    }

    @GetMapping(path = "/{brojPrijave}", produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtev getZahtev(@PathVariable String brojPrijave) throws Exception {
        return zahtevService.getZahtev(brojPrijave);
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
    public Zahtevi search(@RequestBody SearchRequest searchRequest) {
        if(searchRequest.getQuery().size() == 0) return new Zahtevi();
        return new Zahtevi(zahtevService.search(searchRequest.getQuery()));
    }

    @PostMapping(path = "/search-meta", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    @PreAuthorize("hasAnyAuthority('SLUZBENIK', 'GRADJANIN')")
    public Zahtevi metaSearch(@RequestBody MetaSearchRequest metaSearchRequest) throws XMLDBException {
        for (MetaSearchQuery searchQuery: metaSearchRequest.getQuery()) {
            System.out.println(searchQuery.getPredicate() + " " + searchQuery.getObject() + " " + searchQuery.getOperator());
        }
        return new Zahtevi(metadataService.metaSearch(metaSearchRequest));
    }

    @GetMapping(path = "/izvestaj", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> exportIzvestaj(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                   @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) throws DatatypeConfigurationException, XMLDBException {
        return pdfService.exportToResource(izvestajService.generateNew(startDate, endDate));
    }
}
