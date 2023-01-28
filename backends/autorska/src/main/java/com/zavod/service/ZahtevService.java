package com.zavod.service;

import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.List;

@Service
public class ZahtevService {

	@Autowired
	public ZahtevRepository zahtevRepository;

	@Autowired
	public MetadataRepository metadataRepository;

	public List<Zahtev> getAll() {
		return zahtevRepository.getAll();
	}

	public Zahtev getZahtev(String id) throws XMLDBException {
		return zahtevRepository.findById(id);
	}

	public void addZahtev(Zahtev zahtev) {
		this.zahtevRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
	}

	public List<Zahtev> search(List<String> query) {
		return zahtevRepository.search(query);
	}
}
