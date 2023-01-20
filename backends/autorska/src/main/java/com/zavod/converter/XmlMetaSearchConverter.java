package com.zavod.converter;

import com.zavod.dto.MetaSearchRequest;
import com.zavod.dto.SearchRequest;
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

public class XmlMetaSearchConverter extends AbstractHttpMessageConverter<MetaSearchRequest> {

    public XmlMetaSearchConverter() {

    }

    public XmlMetaSearchConverter(MediaType mt) {
        super(mt);
    }

    public XmlMetaSearchConverter(MediaType... mt) {
        super(mt);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return MetaSearchRequest.class.equals(clazz);
    }

    @Override
    protected MetaSearchRequest readInternal(Class<? extends MetaSearchRequest> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MetaSearchRequest.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (MetaSearchRequest) unmarshaller.unmarshal(inputMessage.getBody());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeInternal(MetaSearchRequest zahtev, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try {
            JAXBContext context = JAXBContext.newInstance(MetaSearchRequest.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtev, outputMessage.getBody());
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}