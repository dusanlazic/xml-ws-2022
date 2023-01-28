package com.zavod.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

public class ServiceUtil {

    public static String brojToHumanReadable(String brojPrijave) {
        String retVal = brojPrijave.replace("Z","Ж");
        int idx = retVal.lastIndexOf("-");
        if (idx != -1)
            return retVal.substring(0, idx) + "/" + retVal.substring(idx + 1);
        return retVal;
    }

    public static XMLGregorianCalendar today() throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar xmlDate = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

        xmlDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        xmlDate.setTime(DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED,
                DatatypeConstants.FIELD_UNDEFINED);

        return xmlDate;
    }

}
