package com.zavod.service;

import com.zavod.dto.KorisnikDTO;
import com.zavod.dto.Kredencijali;
import com.zavod.dto.TokenDTO;
import com.zavod.exception.EmailAlreadyInUseException;
import com.zavod.exception.WrongCredentialsException;
import com.zavod.model.Korisnik;
import com.zavod.repository.KorisnikRepository;
import com.zavod.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void register(Korisnik korisnik) {
        if (korisnikRepository.existsByEmail(korisnik.getEmail()))
            throw new EmailAlreadyInUseException();

        korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
        this.korisnikRepository.save(korisnik, korisnik.getId() + ".xml");
    }
}
