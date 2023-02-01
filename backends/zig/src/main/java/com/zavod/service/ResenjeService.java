package com.zavod.service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.ResenjeDTO;
import com.zavod.model.resenje.*;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ResenjeRepository;
import com.zavod.repository.ZahtevRepository;
import com.zavod.util.QRCodeEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.datatype.DatatypeConfigurationException;

import java.io.IOException;

import static com.zavod.util.ServiceUtil.*;

@Service
public class ResenjeService {

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private ResenjeRepository resenjeRepository;

    @Autowired
    private MetadataRepository metadataRepository;

    public Resenje addResenje(ResenjeDTO resenjeDTO, String brojPrijave, Authentication authentication) throws XMLDBException, DatatypeConfigurationException {
        KorisnikDTO korisnikDTO = (KorisnikDTO) authentication.getPrincipal();
        Zahtev zahtev = zahtevRepository.findById(brojPrijave);

        Resenje resenje = new Resenje(
                new TSluzbenik(korisnikDTO.getIme(), korisnikDTO.getPrezime()),
                new TInformacijeOZahtevu(brojPrijave),
                new TOdluka(today(), resenjeDTO.getOdluka().getObrazlozenje(), resenjeDTO.getOdluka().isPrihvacen())
        );

        if (resenje.getOdluka().isPrihvacen()) {
            updateMetadata(zahtev, StatusResenja.PRIHVACEN);
            zahtev.getInformacijeZavoda().setStatusResenja(StatusResenja.PRIHVACEN.toString());
        } else {
            updateMetadata(zahtev, StatusResenja.ODBIJEN);
            zahtev.getInformacijeZavoda().setStatusResenja(StatusResenja.ODBIJEN.toString());
        }

        zahtevRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
        resenjeRepository.save(resenje, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
        return resenje;
    }

    public Resenje getResenje(String brojPrijave) throws XMLDBException {
        return resenjeRepository.findById(brojPrijave);
    }

    private void updateMetadata(Zahtev zahtev, StatusResenja status) {
        metadataRepository.updateRdf(
                "http://www.zavod.com/Zig/" + zahtev.getInformacijeZavoda().getBrojPrijave(),
                "http://www.zavod.com/Zig/pred/Status_resenja",
                status.toString()
        );
    }

    public ResponseEntity<byte[]> qrCodeToResource(String brojPrijave) throws BadElementException, IOException, WriterException {
        String url = "http://localhost:8082/resenja/" + brojPrijave;
        return QRCodeEncoder.generateQRCodeImage(url);
    }
}
