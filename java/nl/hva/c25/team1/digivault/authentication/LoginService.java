// Created by antho
// Creation date 15-12-2021

package nl.hva.c25.team1.digivault.authentication;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anneke en Anthon
 */
@Service
public class LoginService {

    private HashService hashService;
    private TokenService tokenService;
    private AccountDAO accountDAO;

    @Autowired
    public LoginService(HashService hashService, TokenService tokenService, AccountDAO accountDAO) {
        this.hashService = hashService;
        this.tokenService = tokenService;
        this.accountDAO = accountDAO;
    }

    /**
     *
     * Deze methode wordt aangeroepen vanuit de LoginController
     * en krijgt het door de gebruiker ingevulde wachtwoord en emailadres mee
     * Via de hashservice wordt gecheckt of het wachtwoord een match is met dat van de gebruiker
     * Zo ja, dan wordt het JWT token aangemaakt
     * @param emailAdres van klant die probeert in te loggen
     * @param password van klant die probeert in te loggen
     * @return JWT token
     */
    public String login(String emailAdres, String password) {
        String token = null;
        Account account = accountDAO.vindAccountOpEmailAdres(emailAdres);
        if (account != null) {
            String storedHash = account.getWachtwoord();
            if (hashService.matches(password, storedHash)) {
                token = tokenService.maakJWT(emailAdres);
            }
        }
        return token;
    }

}