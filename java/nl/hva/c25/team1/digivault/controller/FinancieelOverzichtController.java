package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.authentication.TokenService;
import nl.hva.c25.team1.digivault.model.FinancieelOverzicht;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.AccountService;
import nl.hva.c25.team1.digivault.service.FinancieelOverzichtService;
import nl.hva.c25.team1.digivault.service.KlantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller van de klasse FinancieelOverzicht
 *
 * @author Erwin en Nienke
 * @since 9-12-2021
 */

@RestController
@CrossOrigin
public class FinancieelOverzichtController {

    private final FinancieelOverzichtService financieelOverzichtService;
    private final TokenService tokenService;
    private final AccountService accountService;
    private final KlantService klantService;

    /**
     * Constructor van de FinancieelOverzichtController
     * @param financieelOverzichtService FinancieelOverzichtService
     */
    public FinancieelOverzichtController(FinancieelOverzichtService financieelOverzichtService,
                                         TokenService tokenService, AccountService accountService,
                                         KlantService klantService) {
        super();
        this.financieelOverzichtService = financieelOverzichtService;
        this.tokenService = tokenService;
        this.accountService = accountService;
        this.klantService = klantService;
    }

    /**
     * Genereren van een financieel overzicht (rekening/portefeuille) op klantId
     * @return een FinancieelOverzicht
     */
    @PostMapping("/financieeloverzicht")
    public ResponseEntity<FinancieelOverzicht> vindFinancieelOverzicht(@RequestHeader("Authorization") String token) {
        String emailadres = tokenService.getEmailadresToken(token);
        Klant klant = klantService.vindKlantOpEmail(emailadres);
        int klantId = klant.getTransactiepartijId();
        boolean authorized = tokenService.getEmailadresToken(token).equals(accountService.vindAccountOpKlantId(klantId).
                getEmailadres());
        if (tokenService.valideerJWT(token) && authorized) {
            return ResponseEntity.ok(financieelOverzichtService.genereerFinancieelOverzicht(klantId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
