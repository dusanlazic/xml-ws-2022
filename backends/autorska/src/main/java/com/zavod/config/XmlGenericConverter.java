package com.zavod.config;

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
import java.io.FileNotFoundException;
import java.io.IOException;

public class XmlGenericConverter<T> extends AbstractHttpMessageConverter<T> {

    private Class<T> clazz;

    public XmlGenericConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    public XmlGenericConverter(Class<T> clazz, MediaType mt) {
        super(mt);
        this.clazz = clazz;
    }

    public XmlGenericConverter(Class<T> clazz, MediaType... mt) {
        super(mt);
        this.clazz = clazz;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return this.clazz.equals(clazz);
    }

    @Override
    protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(this.clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(inputMessage.getBody());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeInternal(T zahtev, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try {
            JAXBContext context = JAXBContext.newInstance(this.clazz);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtev, outputMessage.getBody());
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}