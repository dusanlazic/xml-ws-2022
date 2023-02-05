package com.zavod.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ServiceUtil {

    public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static String brojToHumanReadable(String brojPrijave) {
        return brojPrijave
                .replace("Z","Ž")
                .replaceFirst("-", " ")
                .replace("-", "/");
    }

    public static XMLGregorianCalendar xmlDatefromDate(Date date) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }

    public static XMLGregorianCalendar stripTime(XMLGregorianCalendar xmlDate) {
        xmlDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        xmlDate.setTime(DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED);
        return xmlDate;
    }

    public static XMLGregorianCalendar today() throws DatatypeConfigurationException {
        return stripTime(xmlDatefromDate(new Date()));
    }

    public static String dateToString(Date date) {
        return df.format(date);
    }

}
