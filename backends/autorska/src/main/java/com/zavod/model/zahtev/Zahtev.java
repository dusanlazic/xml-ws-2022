//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.19 at 10:43:35 PM CET
//


package com.zavod.model.zahtev;



import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZahtev", propOrder = {
        "podnosilac",
        "delo",
        "autor",
        "informacijeZavoda",
        "punomocnik",
        "informacijeSistema"
})
@XmlRootElement(name = "Zahtev")
public class Zahtev {

    @XmlElement(name = "Podnosilac", required = true)
    protected TLice podnosilac;

    @XmlElement(name = "Delo", required = true)
    protected TDelo delo;

    @XmlElement(name = "Autori", required = true)
    protected Autori autor;
    @XmlElement(name = "Informacije_Zavoda", required = true)
    protected TInformacijeZavoda informacijeZavoda;
    @XmlElement(name = "Punomocnik")
    protected TFizickoLice punomocnik;
    @XmlElement(name = "Informacije_Sistema", required = true)
    protected TInformacijeSistema informacijeSistema;

    public TLice getPodnosilac() {
        return podnosilac;
    }


    public void setPodnosilac(TLice value) {
        this.podnosilac = value;
    }


    public TDelo getDelo() {
        return delo;
    }


    public void setDelo(TDelo value) {
        this.delo = value;
    }


    public Autori getAutor() {
        if (autor == null) {
            autor = new Autori();
        }
        return this.autor;
    }


    public void setAutor(Autori value) {
        this.autor = value;
    }


    public TInformacijeZavoda getInformacijeZavoda() {
        return informacijeZavoda;
    }

    public void setInformacijeZavoda(TInformacijeZavoda value) {
        this.informacijeZavoda = value;
    }


    public TFizickoLice getPunomocnik() {
        return punomocnik;
    }

    public void setPunomocnik(TFizickoLice value) {
        this.punomocnik = value;
    }

    public TInformacijeSistema getInformacijeSistema() {
        return informacijeSistema;
    }

    public void setInformacijeSistema(TInformacijeSistema value) {
        this.informacijeSistema = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TAutori", propOrder = {
            "podnosilacJeAutor",
            "autorJeAnoniman",
            "autor"
    })
    public static class Autori {

        @XmlElement(name = "podnosilac_je_autor", required = true)
        protected boolean podnosilacJeAutor = false;

        @XmlElement(name = "autor_je_anoniman", required = true)
        protected boolean autorJeAnoniman = false;

        @XmlElement(name = "Autor")
        protected List<Autor> autor;

        public List<Autor> getAutor() {
            if (autor == null) {
                autor = new ArrayList<Autor>();
            }
            return this.autor;
        }

        public void setAutor(List<Autor> value) {
            this.autor = value;
        }

        public boolean getPodnosilacJeAutor() {
            return podnosilacJeAutor;
        }

        public void setPodnosilacJeAutor(boolean value) {
            this.podnosilacJeAutor = value;
        }

        public boolean getAutorJeAnoniman() {
            return autorJeAnoniman;
        }

        public void setAutorJeAnoniman(boolean value) {
            this.autorJeAnoniman = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TAutor", propOrder = {
            "godinaSmrti"
    })
    public static class Autor extends TFizickoLice
    {

        @XmlElement(name = "godina_smrti")
        protected XMLGregorianCalendar godinaSmrti;

        public XMLGregorianCalendar getGodinaSmrti() {
            return godinaSmrti;
        }

        public void setGodinaSmrti(XMLGregorianCalendar value) {
            this.godinaSmrti = value;
        }

    }

}
