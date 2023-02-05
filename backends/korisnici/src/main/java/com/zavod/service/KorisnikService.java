package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.KorisnikRegisterDTO;
import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import com.zavod.exception.EmailAlreadyInUseException;
import com.zavod.exception.WrongCredentialsException;
import com.zavod.model.Korisnik;
import com.zavod.repository.KorisnikRepository;
import com.zavod.security.TokenProvider;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;

import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    public KorisnikRepository korisnikRepository;

    @Autowired
    public TokenProvider tokenProvider;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public TokenDTO login(Kredencijali kredencijali) {
        Optional<Korisnik> korisnik = korisnikRepository.getAll().stream().filter(k ->
                k.getEmail().equals(kredencijali.getEmail()) && passwordEncoder.matches(kredencijali.getLozinka(), k.getLozinka())
        ).findFirst();

        boolean passwordsMatch = korisnik.isPresent();

        if (!passwordsMatch)
            throw new WrongCredentialsException();

        return new TokenDTO(tokenProvider.createAccessToken(korisnik.get()), new KorisnikDTO(korisnik.get()));
    }

    public void register(KorisnikRegisterDTO korisnikDto) {
        if (korisnikRepository.existsByEmail(korisnikDto.getEmail()))
            throw new EmailAlreadyInUseException();

        Korisnik korisnik = new Korisnik(
                getMaxId() + 1,
                korisnikDto.getEmail(),
                passwordEncoder.encode(korisnikDto.getLozinka()),
                korisnikDto.getIme(),
                korisnikDto.getPrezime(),
                korisnikDto.getUloga()
        );

        this.korisnikRepository.save(korisnik, korisnik.getId() + ".xml");
    }

    private long getMaxId() {
        long maxId = 0;
        try {
            ResourceSet results = korisnikRepository.executeXPath("//Korisnik/id/text()");
            ResourceIterator i = results.getIterator();
            while (i.hasMoreResources()) {
                long id = Long.parseLong(i.nextResource().getContent().toString());
                if (id > maxId) {
                    maxId = id;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting max id.");
        }
        return maxId;
    }
}
