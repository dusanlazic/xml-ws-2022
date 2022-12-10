//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.10 at 06:23:23 PM CET 
//


package com.zavod.model.zig;

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
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="individualni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="kolektivni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="garantni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="tip_znaka">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="verbalni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="kombinovani" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="trodimenzionalni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="druga_vrsta" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
 *                   &lt;element name="klasa">
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
    protected TZig.TipZiga tipZiga;
    @XmlElement(name = "tip_znaka", required = true)
    protected TZig.TipZnaka tipZnaka;
    @XmlElement(required = true)
    protected String boja;
    @XmlElement(required = true)
    protected String transliteracija;
    @XmlElement(required = true)
    protected String prevod;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(name = "klase_robe", required = true)
    protected TZig.KlaseRobe klaseRobe;
    @XmlElement(name = "pravo_prvenstva", required = true)
    protected String pravoPrvenstva;

    /**
     * Gets the value of the tipZiga property.
     * 
     * @return
     *     possible object is
     *     {@link TZig.TipZiga }
     *     
     */
    public TZig.TipZiga getTipZiga() {
        return tipZiga;
    }

    /**
     * Sets the value of the tipZiga property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZig.TipZiga }
     *     
     */
    public void setTipZiga(TZig.TipZiga value) {
        this.tipZiga = value;
    }

    /**
     * Gets the value of the tipZnaka property.
     * 
     * @return
     *     possible object is
     *     {@link TZig.TipZnaka }
     *     
     */
    public TZig.TipZnaka getTipZnaka() {
        return tipZnaka;
    }

    /**
     * Sets the value of the tipZnaka property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZig.TipZnaka }
     *     
     */
    public void setTipZnaka(TZig.TipZnaka value) {
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
     *     {@link TZig.KlaseRobe }
     *     
     */
    public TZig.KlaseRobe getKlaseRobe() {
        return klaseRobe;
    }

    /**
     * Sets the value of the klaseRobe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZig.KlaseRobe }
     *     
     */
    public void setKlaseRobe(TZig.KlaseRobe value) {
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
     *         &lt;element name="klasa">
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

        protected int klasa;

        /**
         * Gets the value of the klasa property.
         * 
         */
        public int getKlasa() {
            return klasa;
        }

        /**
         * Sets the value of the klasa property.
         * 
         */
        public void setKlasa(int value) {
            this.klasa = value;
        }

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
     *       &lt;choice>
     *         &lt;element name="individualni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="kolektivni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="garantni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "individualni",
        "kolektivni",
        "garantni"
    })
    public static class TipZiga {

        protected Object individualni;
        protected Object kolektivni;
        protected Object garantni;

        /**
         * Gets the value of the individualni property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getIndividualni() {
            return individualni;
        }

        /**
         * Sets the value of the individualni property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setIndividualni(Object value) {
            this.individualni = value;
        }

        /**
         * Gets the value of the kolektivni property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getKolektivni() {
            return kolektivni;
        }

        /**
         * Sets the value of the kolektivni property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setKolektivni(Object value) {
            this.kolektivni = value;
        }

        /**
         * Gets the value of the garantni property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getGarantni() {
            return garantni;
        }

        /**
         * Sets the value of the garantni property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setGarantni(Object value) {
            this.garantni = value;
        }

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
     *       &lt;choice>
     *         &lt;element name="verbalni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="kombinovani" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="trodimenzionalni" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="druga_vrsta" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "verbalni",
        "kombinovani",
        "trodimenzionalni",
        "drugaVrsta"
    })
    public static class TipZnaka {

        protected Object verbalni;
        protected Object kombinovani;
        protected Object trodimenzionalni;
        @XmlElement(name = "druga_vrsta")
        protected Object drugaVrsta;

        /**
         * Gets the value of the verbalni property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getVerbalni() {
            return verbalni;
        }

        /**
         * Sets the value of the verbalni property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setVerbalni(Object value) {
            this.verbalni = value;
        }

        /**
         * Gets the value of the kombinovani property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getKombinovani() {
            return kombinovani;
        }

        /**
         * Sets the value of the kombinovani property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setKombinovani(Object value) {
            this.kombinovani = value;
        }

        /**
         * Gets the value of the trodimenzionalni property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getTrodimenzionalni() {
            return trodimenzionalni;
        }

        /**
         * Sets the value of the trodimenzionalni property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setTrodimenzionalni(Object value) {
            this.trodimenzionalni = value;
        }

        /**
         * Gets the value of the drugaVrsta property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getDrugaVrsta() {
            return drugaVrsta;
        }

        /**
         * Sets the value of the drugaVrsta property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setDrugaVrsta(Object value) {
            this.drugaVrsta = value;
        }

    }

}
