package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.service.*;
import nl.hva.c25.team1.digivault.transfer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Deze controller handelt requests af met betrekking tot transacties.
 *
 * @author Nienke
 * @author Anthon
 *
 * bugfix door Anneke en Anthon
 */
@RestController
@CrossOrigin
public class TransactieController {

    private TransactieService transactieService;
    private TokenService tokenService;
    private AccountService accountService;
    private KlantService klantService;

    @Autowired
    public TransactieController(TransactieService transactieService, TokenService tokenService,
                                AccountService accountService, KlantService klantService) {
        this.transactieService = transactieService;
        this.tokenService = tokenService;
        this.accountService = accountService;
        this.klantService = klantService;
    }

    /*
     * Deze request-handler verwerkt een transactie. Eerst worden authenticatie en autorisatie gecheckt; vervolgens
     * wordt de DTO doorgezet voor verwerking.
     */
    @PostMapping("/transactie")
    public ResponseEntity<String> transactieHandler(
            @RequestHeader("Authorization") String token,
            @RequestBody TransactieDTO transactieDTO) {
        String emailadres = tokenService.getEmailadresToken(token);
        int klantId = klantService.vindKlantOpEmail(emailadres).getTransactiepartijId();
        boolean authorized = emailadres.equals(accountService.vindAccountOpKlantId(klantId).getEmailadres());
        if (tokenService.valideerJWT(token) && authorized) {
            return ResponseEntity.ok(probeerTransactieOmTeZettenEnUitTeVoeren(transactieDTO));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /*
     * Deze methode probeert de DTO om te zetten naar een transactie-object. Vervolgens wordt deze transactie
     * doorgegeven aan de service-laag.
     */
    String probeerTransactieOmTeZettenEnUitTeVoeren(TransactieDTO transactieDTO) {
        TransactieMapper transactieMapper = new TransactieMapper();
        Transactie transactie;
        try {
            transactie = transactieMapper.toObject(transactieDTO);
        }
        catch(IllegalArgumentException illegalArgumentException) {
            return illegalArgumentException.getMessage();
        }
        if (transactie != null) {
            transactie = transactieService.voerTransactieUit(transactie);
        }
        if (transactie == null) {
            return "transaction failed";
        }
        return "transaction executed";
    }

    @GetMapping("/transactie/{transactieId}")
    public Transactie vindTransactieOpTransactieIdHandler(@PathVariable int transactieId) {
        return transactieService.vindTransactieOpTransactieId(transactieId);
    }

}
