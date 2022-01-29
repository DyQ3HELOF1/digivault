// Created by antho
// Creation date 13-12-2021

package nl.hva.c25.team1.digivault.authentication;

import org.springframework.stereotype.Service;

@Service
public class PepperService {

    private static final String PEPPER = "ToBeOrNotToBeThoseAreTheOptions";

    public PepperService() {
        super();
    }

    public String getPepper() {
        return PEPPER;
    }

}