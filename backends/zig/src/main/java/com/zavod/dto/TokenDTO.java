package com.zavod.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Token", propOrder = {
        "access_token",
        "korisnik"
})
@XmlRootElement(name = "Token")
public class TokenDTO {

    private String access_token;
    private KorisnikDTO korisnik;

    public TokenDTO() {
    }

    public TokenDTO(String access_token, KorisnikDTO korisnik) {
        this.access_token = access_token;
        this.korisnik = korisnik;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }
}
