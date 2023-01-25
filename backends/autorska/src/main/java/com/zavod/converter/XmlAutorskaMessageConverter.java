package com.zavod.converter;

import com.zavod.model.Zahtev;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlAutorskaMessageConverter extends AbstractHttpMessageConverter<Zahtev> {

    public XmlAutorskaMessageConverter() {

    }

    public XmlAutorskaMessageConverter(MediaType mt) {
        super(mt);
    }

    public XmlAutorskaMessageConverter(MediaType... mt) {
        super(mt);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Zahtev.class.equals(clazz);
    }

    @Override
    protected Zahtev readInternal(Class<? extends Zahtev> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Zahtev.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Zahtev) unmarshaller.unmarshal(inputMessage.getBody());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeInternal(Zahtev zahtev, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try {
            JAXBContext context = JAXBContext.newInstance(Zahtev.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtev, outputMessage.getBody());
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
