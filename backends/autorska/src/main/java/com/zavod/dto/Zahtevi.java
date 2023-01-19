package com.zavod.dto;

import com.zavod.model.Zahtev;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Zahtevi")
public class Zahtevi {
    private List<Zahtev> zahtevi;

    public Zahtevi(List<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }

    public Zahtevi() {
    }

//    @XmlElementWrapper
    @XmlElement(name="Zahtevi")
    public List<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }
}
