//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.28 at 07:12:40 PM CET 
//


package com.zavod.model.resenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.zavod.resenje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Resenje_QNAME = new QName("http://www.zavod.com/Resenje", "Resenje");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.zavod.resenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TOdluka }
     * 
     */
    public TOdluka createTOdluka() {
        return new TOdluka();
    }

    /**
     * Create an instance of {@link Resenje }
     * 
     */
    public Resenje createTResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link TSluzbenik }
     * 
     */
    public TSluzbenik createTSluzbenik() {
        return new TSluzbenik();
    }

    /**
     * Create an instance of {@link TInformacijeOZahtevu }
     * 
     */
    public TInformacijeOZahtevu createTInformacijeOZahtevu() {
        return new TInformacijeOZahtevu();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resenje }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.zavod.com/Resenje", name = "Resenje")
    public JAXBElement<Resenje> createResenje(Resenje value) {
        return new JAXBElement<Resenje>(_Resenje_QNAME, Resenje.class, null, value);
    }

}