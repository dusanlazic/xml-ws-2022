//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.28 at 07:12:40 PM CET 
//


package com.zavod.model.resenje;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for TResenje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TResenje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sluzbenik" type="{http://www.zavod.com/Resenje}TSluzbenik"/>
 *         &lt;element name="zahtev" type="{http://www.zavod.com/Resenje}TInformacije_o_Zahtevu"/>
 *         &lt;element name="odluka" type="{http://www.zavod.com/Resenje}TOdluka"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TResenje", propOrder = {
    "sluzbenik",
    "zahtev",
    "odluka"
})
@XmlRootElement(name = "Resenje")
public class Resenje {

    @XmlElement(required = true)
    protected TSluzbenik sluzbenik;
    @XmlElement(required = true)
    protected TInformacijeOZahtevu zahtev;
    @XmlElement(required = true)
    protected TOdluka odluka;

    public Resenje() {
    }

    public Resenje(TSluzbenik sluzbenik, TInformacijeOZahtevu zahtev, TOdluka odluka) {
        this.sluzbenik = sluzbenik;
        this.zahtev = zahtev;
        this.odluka = odluka;
    }

    /**
     * Gets the value of the sluzbenik property.
     * 
     * @return
     *     possible object is
     *     {@link TSluzbenik }
     *     
     */
    public TSluzbenik getSluzbenik() {
        return sluzbenik;
    }

    /**
     * Sets the value of the sluzbenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSluzbenik }
     *     
     */
    public void setSluzbenik(TSluzbenik value) {
        this.sluzbenik = value;
    }

    /**
     * Gets the value of the zahtev property.
     * 
     * @return
     *     possible object is
     *     {@link TInformacijeOZahtevu }
     *     
     */
    public TInformacijeOZahtevu getZahtev() {
        return zahtev;
    }

    /**
     * Sets the value of the zahtev property.
     * 
     * @param value
     *     allowed object is
     *     {@link TInformacijeOZahtevu }
     *     
     */
    public void setZahtev(TInformacijeOZahtevu value) {
        this.zahtev = value;
    }

    /**
     * Gets the value of the odluka property.
     * 
     * @return
     *     possible object is
     *     {@link TOdluka }
     *     
     */
    public TOdluka getOdluka() {
        return odluka;
    }

    /**
     * Sets the value of the odluka property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOdluka }
     *     
     */
    public void setOdluka(TOdluka value) {
        this.odluka = value;
    }

}
