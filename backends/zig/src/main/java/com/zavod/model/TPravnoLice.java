//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 12:14:51 AM CET 
//


package com.zavod.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPravno_Lice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPravno_Lice">
 *   &lt;complexContent>
 *     &lt;extension base="{www.zavod.com/Zig}TLice">
 *       &lt;sequence>
 *         &lt;element name="poslovno_ime" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPravno_Lice", propOrder = {
    "poslovnoIme"
})
public class TPravnoLice
    extends TLice
{

    @XmlElement(name = "poslovno_ime", required = true)
    protected Object poslovnoIme;

    /**
     * Gets the value of the poslovnoIme property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPoslovnoIme() {
        return poslovnoIme;
    }

    /**
     * Sets the value of the poslovnoIme property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPoslovnoIme(Object value) {
        this.poslovnoIme = value;
    }

}