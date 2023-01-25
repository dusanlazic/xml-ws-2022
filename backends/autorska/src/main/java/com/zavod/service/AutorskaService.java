package com.zavod.service;

import com.zavod.dto.MetaSearchQuery;
import com.zavod.dto.MetaSearchRequest;
import com.zavod.model.Zahtev;
import com.zavod.repository.AutorskaRepository;
import com.zavod.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AutorskaService {

	@Autowired
	public AutorskaRepository autorskaRepository;

	@Autowired
	public MetadataRepository metadataRepository;


	public List<Zahtev> getAll() {
		return autorskaRepository.getAll();
	}

	public Zahtev getZahtev(String id) throws XMLDBException {
		return autorskaRepository.findById(id);
	}

	public void addZahtev(Zahtev zahtev) {
		this.autorskaRepository.save(zahtev, zahtev.getInformacijeZavoda().getBrojPrijave() + ".xml");
	}

	public List<Zahtev> search(List<String> query) {
		return autorskaRepository.search(query);
	}


}
