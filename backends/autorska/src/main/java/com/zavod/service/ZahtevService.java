package com.zavod.service;

import com.zavod.model.resenje.StatusResenja;
import com.zavod.model.zahtev.Zahtev;
import com.zavod.repository.MetadataRepository;
import com.zavod.repository.ZahtevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<Zahtev> search(List<String> query, boolean showOnlyPrihvaceni) {
		if (showOnlyPrihvaceni)
			return zahtevRepository.search(query).stream()
					.filter(z -> z.getInformacijeZavoda().getStatusResenja().equals(StatusResenja.PRIHVACEN.toString()))
					.collect(Collectors.toList());
		return zahtevRepository.search(query);
	}
}
