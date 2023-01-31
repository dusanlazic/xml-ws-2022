//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.28 at 07:12:40 PM CET 
//


package com.zavod.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TOdluka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TOdluka">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="obrazlozenje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prihvacen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOdluka", propOrder = {
    "obrazlozenje",
    "prihvacen",
    "dostavioPrimer",
    "dostavioOpis"
})
public class OdlukaDTO {

    @XmlElement(required = true)
    protected String obrazlozenje;
    @XmlElement(required = true)
    protected boolean prihvacen;
    @XmlElement(name = "dostavio_primer", required = true)
    protected boolean dostavioPrimer;
    @XmlElement(name = "dostavio_opis", required = true)
    protected boolean dostavioOpis;

    public String getObrazlozenje() {
        return obrazlozenje;
    }

    public void setObrazlozenje(String value) {
        this.obrazlozenje = value;
    }

    public boolean isPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(boolean value) {
        this.prihvacen = value;
    }

    public boolean getDostavioPrimer() {
        return dostavioPrimer;
    }
    public void setDostavioPrimer(boolean dostavio) {
        this.dostavioPrimer = dostavio;
    }

    public boolean getDostavioOpis() {
        return dostavioOpis;
    }
    public void setDostavioOpis(boolean dostavio) {
        this.dostavioOpis = dostavio;
    }

}
