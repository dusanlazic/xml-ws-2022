package com.zavod.service;

import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.model.izvestaj.Izvestaj;
import com.zavod.model.resenje.StatusResenja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Arrays;
import java.util.Date;

import static com.zavod.util.ServiceUtil.*;

@Service
public class IzvestajService {

    @Autowired
    private MetadataService metadataService;

    public Izvestaj generateNew(Date startDate, Date endDate) throws DatatypeConfigurationException, XMLDBException {
        int brojPodnetih = metadataService.metaSearch(new MetaSearchRequest(
                Arrays.asList(
                        new MetaSearchQuery("Datum_podnosenja", "<", dateToString(endDate), "I"),
                        new MetaSearchQuery("Datum_podnosenja", ">", dateToString(startDate), "I")
                )
        )).size();

        int brojPrihvacenih = metadataService.metaSearch(new MetaSearchRequest(
                Arrays.asList(
                        new MetaSearchQuery("Datum_podnosenja", "<", dateToString(endDate), "I"),
                        new MetaSearchQuery("Datum_podnosenja", ">", dateToString(startDate), "I"),
                        new MetaSearchQuery("Status_resenja", "=", StatusResenja.PRIHVACEN.toString(), "I")
                )
        )).size();

        int brojOdbijenih = metadataService.metaSearch(new MetaSearchRequest(
                Arrays.asList(
                        new MetaSearchQuery("Datum_podnosenja", "<", dateToString(endDate), "I"),
                        new MetaSearchQuery("Datum_podnosenja", ">", dateToString(startDate), "I"),
                        new MetaSearchQuery("Status_resenja", "=", StatusResenja.ODBIJEN.toString(), "I")
                )
        )).size();

        return new Izvestaj(
                brojPodnetih,
                brojPrihvacenih,
                brojOdbijenih,
                stripTime(xmlDatefromDate(startDate)),
                stripTime(xmlDatefromDate(endDate))
        );
    }
}
