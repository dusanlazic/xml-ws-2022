//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.19 at 10:43:35 PM CET 
//


package com.zavod.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPrilog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPrilog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="putanja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="naziv_datoteke" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrilog", propOrder = {
    "putanja",
    "nazivDatoteke"
})
public class TPrilog {

    @XmlElement(required = true)
    protected String putanja;
    @XmlElement(name = "naziv_datoteke", required = true)
    protected String nazivDatoteke;

    /**
     * Gets the value of the putanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPutanja() {
        return putanja;
    }

    /**
     * Sets the value of the putanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPutanja(String value) {
        this.putanja = value;
    }

    /**
     * Gets the value of the nazivDatoteke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivDatoteke() {
        return nazivDatoteke;
    }

    /**
     * Sets the value of the nazivDatoteke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivDatoteke(String value) {
        this.nazivDatoteke = value;
    }

}