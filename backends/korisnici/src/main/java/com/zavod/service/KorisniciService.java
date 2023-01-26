package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import com.zavod.model.Korisnik;
import com.zavod.repository.KorisniciRepository;
import com.zavod.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.util.List;
import java.util.Optional;

@Service
public class KorisniciService {

    @Autowired
    public KorisniciRepository korisniciRepository;

    @Autowired
    public TokenProvider tokenProvider;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public List<Korisnik> getAll() {
        return korisniciRepository.getAll();
    }

    public Korisnik getKorisnik(String id) throws XMLDBException {
        return korisniciRepository.findById(id);
    }

    public void register(Korisnik korisnik) {
        korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
        this.korisniciRepository.save(korisnik, korisnik.getId() + ".xml");
    }

    public List<Korisnik> search(List<String> query) {
        return korisniciRepository.search(query);
    }

    public TokenDTO login(Kredencijali kredencijali) {
        Optional<Korisnik> korisnik = korisniciRepository.getAll().stream().filter(k ->
            k.getEmail().equals(kredencijali.getEmail()) && passwordEncoder.matches(kredencijali.getLozinka(), k.getLozinka())
        ).findFirst();

        if (korisnik.isPresent())
            return new TokenDTO(tokenProvider.createAccessToken(korisnik.get()), new KorisnikDTO(korisnik.get()));
        else
            return new TokenDTO("", null);
    }
}
