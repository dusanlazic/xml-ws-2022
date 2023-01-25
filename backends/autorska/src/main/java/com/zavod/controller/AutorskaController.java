package com.zavod.controller;

import com.zavod.api.ResponseOk;
import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.dto.SearchRequest;
import com.zavod.dto.Zahtevi;
import com.zavod.model.Zahtev;
import com.zavod.service.AutorskaService;
import com.zavod.service.MetadataService;
import com.zavod.service.PDFService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.util.List;

@RestController
@RequestMapping(path = "/autorska")
public class AutorskaController {

    @Autowired
    public AutorskaService autorskaService;

    @Autowired
    public MetadataService metadataService;

    @Autowired
    public PDFService pdfService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_XML_VALUE)
    public Zahtevi getAll() {
        return new Zahtevi(autorskaService.getAll());
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_XML_VALUE, consumes = {MediaType.APPLICATION_XML_VALUE})
    public ResponseOk addZahtev(@RequestBody Zahtev zahtev) {
        System.out.println("Heeeeerre");
        autorskaService.addZahtev(zahtev);
        System.out.println(zahtev);
        return new ResponseOk("Zahtev kreiran");
    }

    @GetMapping(path = "/first", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getFirst() {
        return autorskaService.getAll().get(0);
    }

    @GetMapping(path = "/last", produces = {MediaType.APPLICATION_XML_VALUE})
    public Zahtev getLast() {
        List<Zahtev> zahtevi = autorskaService.getAll();
        if (zahtevi.isEmpty())
            return null;
        return zahtevi.get(zahtevi.size() - 1);
    }


    @GetMapping(path = "pdf")
    public void generatePdf(String id) throws XMLDBException {
        if(id == null || id.isEmpty()) {
            id = autorskaService.getAll().get(0).getInformacijeZavoda().getBrojPrijave();
        }
        pdfService.generateFiles(autorskaService.getZahtev(id));
    }

    @PostMapping(path = "search", produces = {MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE})
    public Zahtevi search(@RequestBody SearchRequest searchRequest) {
        if(searchRequest.getQuery().size() == 0) return new Zahtevi();
        return new Zahtevi(autorskaService.search(searchRequest.getQuery()));
    }

    @PostMapping(path = "search-meta", produces = {MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE})
    public Zahtevi metaSearch(@RequestBody MetaSearchRequest metaSearchRequest) throws XMLDBException {
        for (MetaSearchQuery searchQuery: metaSearchRequest.getQuery()) {
            System.out.println(searchQuery.getPredicate() + " " + searchQuery.getObject() + " " + searchQuery.getOperator());
        }
        return new Zahtevi(metadataService.metaSearch(metaSearchRequest));
    }

    @GetMapping(path = "{id}")
    public Zahtev getZahtev(@PathVariable String id) throws XMLDBException {
        return autorskaService.getZahtev(id);
    }


}
