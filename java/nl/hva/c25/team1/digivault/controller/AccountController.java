package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// review door Erwin, 6 december

/**
 * Controller van de klasse Account
 *Methodes zonder javadoc comments worden niet gebruikt
 *
 * @author Sezi, studentnummer 500889525
 *
 */
@RestController
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/accounts/{klantId}")
    public void updateAccount(@RequestBody Klant klant) {
        accountService.updateAccount(klant);
    }

    @GetMapping("/accounts")
    public List<Account> geefAccountsHandler() {
        return accountService.geefAlleAccounts();
    }

    @GetMapping("/account/{emailadres}")
    public ResponseEntity<Account> vindAccountOpEmailadresHandler(@PathVariable String emailadres) {
        Account account = accountService.vindAccountOpEmailadres(emailadres);
        if (account == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(account);
        }
    }
}
