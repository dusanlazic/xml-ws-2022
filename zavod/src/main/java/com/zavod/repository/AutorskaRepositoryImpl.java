package com.zavod.repository;

import com.zavod.model.autorska.TZahtev;
import com.zavod.model.autorska.Zahtevi;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Repository
public class AutorskaRepositoryImpl implements AutorskaRepository {

    public final String contextPath = "com.zavod.model.autorska";
    public final String dataPath = "./data/a1.xml";

    Zahtevi zahtevi;


    @Override
    public void load() {
        try {

            System.out.println("Unmarshalling\n");

            JAXBContext context = JAXBContext.newInstance("com.zavod.model.autorska");

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Zahtevi zahtevi = (Zahtevi) unmarshaller.unmarshal(new File("./data/a1.xml"));

            printZahtevi(zahtevi);
            this.zahtevi = zahtevi;

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
                                zahtev.getPodnosilac().getIme() + " - " + zahtev.getDelo().getNaslov());
        }
    }

    public void addZahtev(TZahtev zahtev) {
        if(this.zahtevi == null) {
            throw new RuntimeException("Zahtevi nisu ucitani!");
        }

        this.zahtevi.getZahtev().add(zahtev);
    }
}
