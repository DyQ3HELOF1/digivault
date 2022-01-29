package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.RegistratieService;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller voor de registratie van een Klant
 *
 * @author Erwin, studentnummer 500889293
 * @since 8-12-2021
 */

@Validated
@RestController
public class RegistratieController {

    private final RegistratieService registratieService;

    /**
     * Constructor van de RegistratieController
     * @param registratieService RegistratieService
     */
    public RegistratieController(RegistratieService registratieService) {
        super();
        this.registratieService=registratieService;
    }

    /**
     * POST-en van de registratie van een Klant
     * @param registerDto RegisterDto
     * @return String met info over het slagen van de registratie
     */
    @CrossOrigin
    @PostMapping("/registratie")
    public ResponseEntity<String> registratieHandler(@Valid @RequestBody RegisterDto registerDto) {
        Klant klant = new Klant(registerDto);
        registratieService.registratie(klant);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(String.format("\"%s, je registratie is geslaagd.\\nJe ontvangt het volgende " +
                        "rekeningnummer:\\n%s\"",
                klant.getNaam().getVoornaam(), klant.getRekening().getIBAN()), headers, HttpStatus.OK);
    }

}
