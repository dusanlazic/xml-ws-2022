package com.zavod.converter;

import com.zavod.model.zig.TZahtev;
import com.zavod.model.zig.Zahtevi;
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

public class XmlZigMessageConverter extends AbstractHttpMessageConverter<TZahtev> {

    public XmlZigMessageConverter() {

    }

    public XmlZigMessageConverter(MediaType mt) {
        super(mt);
    }

    public XmlZigMessageConverter(MediaType... mt) {
        super(mt);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return TZahtev.class.equals(clazz);
    }

    @Override
    protected TZahtev readInternal(Class<? extends TZahtev> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Zahtevi.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Zahtevi zahtevi = (Zahtevi) unmarshaller.unmarshal(inputMessage.getBody());
            return zahtevi.getZahtev().get(0);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeInternal(TZahtev zahtev, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try {
            JAXBContext context = JAXBContext.newInstance(Zahtevi.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Zahtevi zahtevi = new Zahtevi();
            zahtevi.getZahtev().add(zahtev);
            marshaller.marshal(zahtevi, outputMessage.getBody());
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
