package com.zavod.dto;

import com.zavod.model.Korisnik;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Korisnici", namespace = "http://www.zavod.com/Korisnik")
public class Korisnici {

    private List<Korisnik> korisnici;

    public Korisnici() {
    }

    public Korisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    @XmlElement(name = "Korisnik")
    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }
}
