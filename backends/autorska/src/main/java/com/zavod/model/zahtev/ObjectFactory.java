//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.19 at 10:43:35 PM CET 
//


package com.zavod.model.zahtev;

import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Zahtev_QNAME = new QName("http://www.zavod.com/Autorska", "Zahtev");
    private final static QName _TLicePseudonim_QNAME = new QName("http://www.zavod.com/Autorska", "pseudonim");

    public ObjectFactory() {
    }

    public TInformacijeSistema createTInformacijeSistema() {
        return new TInformacijeSistema();
    }

    public TDelo.Prerada.OriginalniAutor createTDeloPreradaOriginalniAutor() {
        return new TDelo.Prerada.OriginalniAutor();
    }

    public TFizickoLice createTFizickoLice() {
        return new TFizickoLice();
    }

    public TPravnoLice createTPravnoLice() {
        return new TPravnoLice();
    }

    public TDelo createTDelo() {
        return new TDelo();
    }

    public Zahtev createZahtev() {
        return new Zahtev();
    }

    public TPrilog createTPrilog() {
        return new TPrilog();
    }

    public TInformacijeZavoda createTInformacijeZavoda() {
        return new TInformacijeZavoda();
    }

    public TInformacijeZavoda.Prilozi createTInformacijeZavodaPrilozi() {
        return new TInformacijeZavoda.Prilozi();
    }

    public TDelo.Prerada createTDeloPrerada() {
        return new TDelo.Prerada();
    }

    public Zahtev.Autor createZahtevAutor() {
        return new Zahtev.Autor();
    }

}
