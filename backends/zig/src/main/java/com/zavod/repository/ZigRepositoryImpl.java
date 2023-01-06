package com.zavod.repository;

import com.zavod.model.TZahtev;
import com.zavod.model.Zahtevi;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Repository
public class ZigRepositoryImpl implements ZigRepository {

    public final String contextPath = "com.zavod.model";
    public final String dataPath = "./data/z1.xml";

    Zahtevi zahtevi;

    @Override
    public void load() {
        try {
            System.out.println("Unmarshalling...");
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtevi zahtevi = (Zahtevi) unmarshaller.unmarshal(new File(dataPath));
            printZahtevi(zahtevi);
            this.zahtevi = zahtevi;
            System.out.println();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(Zahtevi zahtevi) {
        try {
            System.out.println("Marshalling");
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtevi, new FileOutputStream(dataPath));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Zahtevi getAll() {
        return this.zahtevi;
    }

    private void printZahtevi(Zahtevi zahtevi) {
        for (TZahtev zahtev: zahtevi.getZahtev()) {
            System.out.println(zahtev.getInformacijeZavoda().getBrojPrijave() + " " +
                    zahtev.getPodnosilac().getEmail() + " - " + zahtev.getZig().getOpis());
        }
    }

    public void addZahtev(TZahtev zahtev) {
        if(this.zahtevi == null) {
            throw new RuntimeException("Zahtevi nisu ucitani!");
        }

        this.zahtevi.getZahtev().add(zahtev);
    }
}
