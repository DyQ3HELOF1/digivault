// Created by antho
// Creation date 13-12-2021

package nl.hva.c25.team1.digivault.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    private final PepperService pepperService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public HashService(PepperService pepperService) {
        this.pepperService = pepperService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String hash(String value) {
        return bCryptPasswordEncoder.encode(value + pepperService.getPepper());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword + pepperService.getPepper(), encodedPassword);
    }

}