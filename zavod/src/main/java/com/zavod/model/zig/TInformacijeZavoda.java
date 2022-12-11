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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TInformacije_Zavoda complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TInformacije_Zavoda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="broj_prijave">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="Ж-[0-9]+/[0-9]{2}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="datum_podsnosenja" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="prilozi">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="primerak_znaka" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="punomocje" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="generalno_punomocje_ranije_prilozeno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="punomocje_ce_biti_naknadno_dostavljeno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="opsti_akt_o_kolektivnom_zigu_garancije" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="dokaz_o_pravu_prvenstva" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="dokaz_o_uplati_takse" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
@XmlType(name = "TInformacije_Zavoda", propOrder = {
    "brojPrijave",
    "datumPodsnosenja",
    "prilozi"
})
public class TInformacijeZavoda {

    @XmlElement(name = "broj_prijave", required = true)
    protected String brojPrijave;
    @XmlElement(name = "datum_podsnosenja", required = true)
    protected XMLGregorianCalendar datumPodsnosenja;
    @XmlElement(required = true)
    protected Prilozi prilozi;

    /**
     * Gets the value of the brojPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojPrijave() {
        return brojPrijave;
    }

    /**
     * Sets the value of the brojPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojPrijave(String value) {
        this.brojPrijave = value;
    }

    /**
     * Gets the value of the datumPodsnosenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumPodsnosenja() {
        return datumPodsnosenja;
    }

    /**
     * Sets the value of the datumPodsnosenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumPodsnosenja(XMLGregorianCalendar value) {
        this.datumPodsnosenja = value;
    }

    /**
     * Gets the value of the prilozi property.
     * 
     * @return
     *     possible object is
     *     {@link Prilozi }
     *     
     */
    public Prilozi getPrilozi() {
        return prilozi;
    }

    /**
     * Sets the value of the prilozi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prilozi }
     *     
     */
    public void setPrilozi(Prilozi value) {
        this.prilozi = value;
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
     *         &lt;element name="primerak_znaka" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="punomocje" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="generalno_punomocje_ranije_prilozeno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="punomocje_ce_biti_naknadno_dostavljeno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="opsti_akt_o_kolektivnom_zigu_garancije" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="dokaz_o_pravu_prvenstva" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="dokaz_o_uplati_takse" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
        "primerakZnaka",
        "punomocje",
        "generalnoPunomocjeRanijePrilozeno",
        "punomocjeCeBitiNaknadnoDostavljeno",
        "opstiAktOKolektivnomZiguGarancije",
        "dokazOPravuPrvenstva",
        "dokazOUplatiTakse"
    })
    public static class Prilozi {

        @XmlElement(name = "primerak_znaka")
        protected boolean primerakZnaka;
        protected boolean punomocje;
        @XmlElement(name = "generalno_punomocje_ranije_prilozeno")
        protected boolean generalnoPunomocjeRanijePrilozeno;
        @XmlElement(name = "punomocje_ce_biti_naknadno_dostavljeno")
        protected boolean punomocjeCeBitiNaknadnoDostavljeno;
        @XmlElement(name = "opsti_akt_o_kolektivnom_zigu_garancije")
        protected boolean opstiAktOKolektivnomZiguGarancije;
        @XmlElement(name = "dokaz_o_pravu_prvenstva")
        protected boolean dokazOPravuPrvenstva;
        @XmlElement(name = "dokaz_o_uplati_takse")
        protected boolean dokazOUplatiTakse;

        /**
         * Gets the value of the primerakZnaka property.
         * 
         */
        public boolean isPrimerakZnaka() {
            return primerakZnaka;
        }

        /**
         * Sets the value of the primerakZnaka property.
         * 
         */
        public void setPrimerakZnaka(boolean value) {
            this.primerakZnaka = value;
        }

        /**
         * Gets the value of the punomocje property.
         * 
         */
        public boolean isPunomocje() {
            return punomocje;
        }

        /**
         * Sets the value of the punomocje property.
         * 
         */
        public void setPunomocje(boolean value) {
            this.punomocje = value;
        }

        /**
         * Gets the value of the generalnoPunomocjeRanijePrilozeno property.
         * 
         */
        public boolean isGeneralnoPunomocjeRanijePrilozeno() {
            return generalnoPunomocjeRanijePrilozeno;
        }

        /**
         * Sets the value of the generalnoPunomocjeRanijePrilozeno property.
         * 
         */
        public void setGeneralnoPunomocjeRanijePrilozeno(boolean value) {
            this.generalnoPunomocjeRanijePrilozeno = value;
        }

        /**
         * Gets the value of the punomocjeCeBitiNaknadnoDostavljeno property.
         * 
         */
        public boolean isPunomocjeCeBitiNaknadnoDostavljeno() {
            return punomocjeCeBitiNaknadnoDostavljeno;
        }

        /**
         * Sets the value of the punomocjeCeBitiNaknadnoDostavljeno property.
         * 
         */
        public void setPunomocjeCeBitiNaknadnoDostavljeno(boolean value) {
            this.punomocjeCeBitiNaknadnoDostavljeno = value;
        }

        /**
         * Gets the value of the opstiAktOKolektivnomZiguGarancije property.
         * 
         */
        public boolean isOpstiAktOKolektivnomZiguGarancije() {
            return opstiAktOKolektivnomZiguGarancije;
        }

        /**
         * Sets the value of the opstiAktOKolektivnomZiguGarancije property.
         * 
         */
        public void setOpstiAktOKolektivnomZiguGarancije(boolean value) {
            this.opstiAktOKolektivnomZiguGarancije = value;
        }

        /**
         * Gets the value of the dokazOPravuPrvenstva property.
         * 
         */
        public boolean isDokazOPravuPrvenstva() {
            return dokazOPravuPrvenstva;
        }

        /**
         * Sets the value of the dokazOPravuPrvenstva property.
         * 
         */
        public void setDokazOPravuPrvenstva(boolean value) {
            this.dokazOPravuPrvenstva = value;
        }

        /**
         * Gets the value of the dokazOUplatiTakse property.
         * 
         */
        public boolean isDokazOUplatiTakse() {
            return dokazOUplatiTakse;
        }

        /**
         * Sets the value of the dokazOUplatiTakse property.
         * 
         */
        public void setDokazOUplatiTakse(boolean value) {
            this.dokazOUplatiTakse = value;
        }

    }

}
