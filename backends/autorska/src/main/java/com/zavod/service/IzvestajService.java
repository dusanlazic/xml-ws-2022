package com.zavod.service;

import com.zavod.model.izvestaj.Izvestaj;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Date;

import static com.zavod.util.ServiceUtil.stripTime;
import static com.zavod.util.ServiceUtil.xmlDatefromDate;

@Service
public class IzvestajService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    public Izvestaj generateNew(Date startDate, Date endDate) throws DatatypeConfigurationException {
        return new Izvestaj(
                30,
                10,
                20,
                stripTime(xmlDatefromDate(startDate)),
                stripTime(xmlDatefromDate(endDate))
        );
    }
}
