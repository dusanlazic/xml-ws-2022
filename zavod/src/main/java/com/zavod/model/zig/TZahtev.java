//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 12:14:51 AM CET 
//


package com.zavod.model.zig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TZahtev complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TZahtev">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Podnosilac" type="{www.zavod.com/Zig}TLice"/>
 *         &lt;element name="Punomocnik" type="{www.zavod.com/Zig}TLice" minOccurs="0"/>
 *         &lt;element name="Predstavnik" type="{www.zavod.com/Zig}TLice" minOccurs="0"/>
 *         &lt;element name="Zig" type="{www.zavod.com/Zig}TZig"/>
 *         &lt;element name="Placanje" type="{www.zavod.com/Zig}TPlacanje"/>
 *         &lt;element name="InformacijeZavoda" type="{www.zavod.com/Zig}TInformacije_Zavoda"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZahtev", propOrder = {
    "podnosilac",
    "punomocnik",
    "predstavnik",
    "zig",
    "placanje",
    "informacijeZavoda"
})
public class TZahtev {

    @XmlElement(name = "Podnosilac", required = true)
    protected TLice podnosilac;
    @XmlElement(name = "Punomocnik")
    protected TLice punomocnik;
    @XmlElement(name = "Predstavnik")
    protected TLice predstavnik;
    @XmlElement(name = "Zig", required = true)
    protected TZig zig;
    @XmlElement(name = "Placanje", required = true)
    protected TPlacanje placanje;
    @XmlElement(name = "InformacijeZavoda", required = true)
    protected TInformacijeZavoda informacijeZavoda;

    /**
     * Gets the value of the podnosilac property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPodnosilac(TLice value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the punomocnik property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPunomocnik() {
        return punomocnik;
    }

    /**
     * Sets the value of the punomocnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPunomocnik(TLice value) {
        this.punomocnik = value;
    }

    /**
     * Gets the value of the predstavnik property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPredstavnik() {
        return predstavnik;
    }

    /**
     * Sets the value of the predstavnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPredstavnik(TLice value) {
        this.predstavnik = value;
    }

    /**
     * Gets the value of the zig property.
     * 
     * @return
     *     possible object is
     *     {@link TZig }
     *     
     */
    public TZig getZig() {
        return zig;
    }

    /**
     * Sets the value of the zig property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZig }
     *     
     */
    public void setZig(TZig value) {
        this.zig = value;
    }

    /**
     * Gets the value of the placanje property.
     * 
     * @return
     *     possible object is
     *     {@link TPlacanje }
     *     
     */
    public TPlacanje getPlacanje() {
        return placanje;
    }

    /**
     * Sets the value of the placanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPlacanje }
     *     
     */
    public void setPlacanje(TPlacanje value) {
        this.placanje = value;
    }

    /**
     * Gets the value of the informacijeZavoda property.
     * 
     * @return
     *     possible object is
     *     {@link TInformacijeZavoda }
     *     
     */
    public TInformacijeZavoda getInformacijeZavoda() {
        return informacijeZavoda;
    }

    /**
     * Sets the value of the informacijeZavoda property.
     * 
     * @param value
     *     allowed object is
     *     {@link TInformacijeZavoda }
     *     
     */
    public void setInformacijeZavoda(TInformacijeZavoda value) {
        this.informacijeZavoda = value;
    }

}
