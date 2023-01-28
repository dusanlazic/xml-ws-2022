package com.zavod.dto;

import com.zavod.model.resenje.TInformacijeOZahtevu;
import com.zavod.model.resenje.TOdluka;
import com.zavod.model.resenje.TSluzbenik;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TResenje", propOrder = {
    "odluka"
})
@XmlRootElement(name = "Resenje")
public class ResenjeDTO {

    @XmlElement(required = true)
    protected OdlukaDTO odluka;

    public ResenjeDTO() {
    }

    public ResenjeDTO(OdlukaDTO odluka) {
        this.odluka = odluka;
    }

    public OdlukaDTO getOdluka() {
        return odluka;
    }

    public void setOdluka(OdlukaDTO odluka) {
        this.odluka = odluka;
    }
}
