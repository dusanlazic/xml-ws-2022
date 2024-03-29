//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.25 at 03:07:21 PM CET 
//


package com.zavod.model.zahtev;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TZig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TZig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tip_ziga">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="individualni"/>
 *               &lt;enumeration value="kolektivni"/>
 *               &lt;enumeration value="garantni"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="tip_znaka">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="verbalni"/>
 *               &lt;enumeration value="graficki"/>
 *               &lt;enumeration value="kombinovani"/>
 *               &lt;enumeration value="trodimenzionalni"/>
 *               &lt;enumeration value="druga"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="boja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transliteracija" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prevod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="klase_robe">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="klasa" maxOccurs="45">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *                         &lt;maxInclusive value="45"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pravo_prvenstva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZig", propOrder = {
    "tipZiga",
    "tipZnaka",
    "boja",
    "transliteracija",
    "prevod",
    "opis",
    "klaseRobe",
    "pravoPrvenstva"
})
public class TZig {

    @XmlElement(name = "tip_ziga", required = true)
    protected String tipZiga;
    @XmlElement(name = "tip_znaka", required = true)
    protected String tipZnaka;
    @XmlElement(required = true)
    protected String boja;
    @XmlElement(required = true)
    protected String transliteracija;
    @XmlElement(required = true)
    protected String prevod;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(name = "klase_robe", required = true)
    protected KlaseRobe klaseRobe;
    @XmlElement(name = "pravo_prvenstva", required = true)
    protected String pravoPrvenstva;

    /**
     * Gets the value of the tipZiga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipZiga() {
        return tipZiga;
    }

    /**
     * Sets the value of the tipZiga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipZiga(String value) {
        this.tipZiga = value;
    }

    /**
     * Gets the value of the tipZnaka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipZnaka() {
        return tipZnaka;
    }

    /**
     * Sets the value of the tipZnaka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipZnaka(String value) {
        this.tipZnaka = value;
    }

    /**
     * Gets the value of the boja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoja() {
        return boja;
    }

    /**
     * Sets the value of the boja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoja(String value) {
        this.boja = value;
    }

    /**
     * Gets the value of the transliteracija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransliteracija() {
        return transliteracija;
    }

    /**
     * Sets the value of the transliteracija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransliteracija(String value) {
        this.transliteracija = value;
    }

    /**
     * Gets the value of the prevod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevod() {
        return prevod;
    }

    /**
     * Sets the value of the prevod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevod(String value) {
        this.prevod = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the klaseRobe property.
     * 
     * @return
     *     possible object is
     *     {@link KlaseRobe }
     *     
     */
    public KlaseRobe getKlaseRobe() {
        return klaseRobe;
    }

    /**
     * Sets the value of the klaseRobe property.
     * 
     * @param value
     *     allowed object is
     *     {@link KlaseRobe }
     *     
     */
    public void setKlaseRobe(KlaseRobe value) {
        this.klaseRobe = value;
    }

    /**
     * Gets the value of the pravoPrvenstva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPravoPrvenstva() {
        return pravoPrvenstva;
    }

    /**
     * Sets the value of the pravoPrvenstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPravoPrvenstva(String value) {
        this.pravoPrvenstva = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="klasa" maxOccurs="45">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
     *               &lt;maxInclusive value="45"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "klasa"
    })
    public static class KlaseRobe {

        @XmlElement(type = Integer.class)
        protected List<Integer> klasa;

        /**
         * Gets the value of the klasa property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the klasa property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKlasa().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Integer }
         * 
         * 
         */
        public List<Integer> getKlasa() {
            if (klasa == null) {
                klasa = new ArrayList<Integer>();
            }
            return this.klasa;
        }

    }

}
