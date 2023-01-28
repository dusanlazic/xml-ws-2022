package com.zavod.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Korisnik", propOrder = {
        "email",
        "lozinka",
        "ime",
        "prezime",
        "uloga"
})
@XmlRootElement(name = "Korisnik")
public class KorisnikRegisterDTO {
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String lozinka;
    @XmlElement(required = true)
    protected String ime;
    @XmlElement(required = true)
    protected String prezime;
    @XmlElement(required = true)
    protected String uloga;

    public KorisnikRegisterDTO() {
    }

    public KorisnikRegisterDTO(String email, String lozinka, String ime, String prezime, String uloga) {
        this.email = email;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}