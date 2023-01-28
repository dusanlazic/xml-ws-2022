package com.zavod.dto;

import com.zavod.model.zahtev.Zahtev;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Zahtevi", namespace = "http://www.zavod.com/Autorska")
public class Zahtevi {
    private List<Zahtev> zahtevi;

    public Zahtevi(List<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }

    public Zahtevi() {
    }

//    @XmlElementWrapper
    @XmlElement(name="Zahtev")
    public List<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }
}
