// Created by antho
// Creation date 16-12-2021

package nl.hva.c25.team1.digivault.authentication;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author Anneke en Anthon
 */
@RestController
public class LoginController {

    private LoginService loginService;
    private KlantService klantService;

    @Autowired
    public LoginController(LoginService loginService, KlantService klantservice) {
        super();
        this.loginService = loginService;
        this.klantService = klantservice;
    }

    /**
     *
     * Methode die wordt aangeroepen in JS fetch loginpagina
     * JWT token teruggegeven vanuit LoginService, gecheckt en meegegeven in de header
     * @param account van klant die probeert in te loggen
     * @return JSON string + headers + httpStatus 200
     */
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody Account account) {
        String token = loginService.login(account.getEmailadres(), account.getWachtwoord());
        if (token == null) {
            return new ResponseEntity<>("Email en/of wachtwoord onjuist!", HttpStatus.UNAUTHORIZED);
        } else {
            Klant klant = klantService.vindKlantOpEmail(account.getEmailadres());
            int klantId = klant.getTransactiepartijId();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Bearer", token);
            headers.set("Access-Control-Expose-Headers", "Bearer" );
            return new ResponseEntity<>(klantId, headers, HttpStatus.OK);
        }
    }

}