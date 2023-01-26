package com.zavod.dto;

import com.zavod.model.Korisnik;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Korisnik", propOrder = {
        "id",
        "email",
        "ime",
        "prezime"
})
@XmlRootElement(name = "Korisnik")
public class KorisnikDTO {

    protected long id;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String ime;
    @XmlElement(required = true)
    protected String prezime;

    public KorisnikDTO() {
    }

    public KorisnikDTO(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.email = korisnik.getEmail();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String value) {
        this.ime = value;
    }


    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String value) {
        this.prezime = value;
    }

}