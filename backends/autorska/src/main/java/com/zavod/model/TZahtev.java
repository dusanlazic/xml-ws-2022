//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.06 at 01:03:03 AM CET 
//


package com.zavod.model;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="Podnosilac" type="{http://www.zavod.com/Autorska}TLice"/>
 *         &lt;element name="Delo" type="{http://www.zavod.com/Autorska}TDelo"/>
 *         &lt;element name="Autor" type="{http://www.zavod.com/Autorska}TLice" minOccurs="0"/>
 *         &lt;element name="Informacije_Zavoda" type="{http://www.zavod.com/Autorska}TInformacije_Zavoda"/>
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
    "delo",
    "autor",
    "informacijeZavoda"
})
@XmlRootElement(name = "Zahtev")
public class TZahtev {

    @XmlElement(name = "Podnosilac", required = true)
    protected TLice podnosilac;
    @XmlElement(name = "Delo", required = true)
    protected TDelo delo;
    @XmlElement(name = "Autor")
    protected TLice autor;
    @XmlElement(name = "Informacije_Zavoda", required = true)
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
     * Gets the value of the delo property.
     * 
     * @return
     *     possible object is
     *     {@link TDelo }
     *     
     */
    public TDelo getDelo() {
        return delo;
    }

    /**
     * Sets the value of the delo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDelo }
     *     
     */
    public void setDelo(TDelo value) {
        this.delo = value;
    }

    /**
     * Gets the value of the autor property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getAutor() {
        return autor;
    }

    /**
     * Sets the value of the autor property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setAutor(TLice value) {
        this.autor = value;
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