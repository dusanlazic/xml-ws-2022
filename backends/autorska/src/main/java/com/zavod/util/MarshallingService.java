package com.zavod.util;

import com.zavod.model.Zahtevi;
import org.w3c.dom.Node;

import javax.xml.bind.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class MarshallingService<T> {

    private final Class<T> type;

    public MarshallingService(Class<T> type) {
        this.type = type;
    }

    public T unmarshall(InputStream is) {
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(is);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public T unmarshall(Node node) {
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(node);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void marshall(T t, OutputStream os) {
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(t, os);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public String marshallString(T t) {
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new ByteArrayOutputStream();
            marshaller.marshal(t, os);
            return os.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
