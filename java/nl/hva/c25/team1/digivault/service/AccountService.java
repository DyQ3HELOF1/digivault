package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.AccountDAO;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// review door Erwin, 6 december

/**
 * @author Sezi, studentnummer 500889525
 *
 */

@Service
public class AccountService {

    private AccountDAO accountDAO;
    private KlantDAO klantDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO, KlantDAO klantDAO) {
        this.accountDAO = accountDAO;
        this.klantDAO = klantDAO;
    }

    /**
     *
     * update een bestaande account uit de database
     * @param klant die geupdate moet worden
     * @return String melding die aangeeft of update geslaagd is
     */

    public String updateAccount(Klant klant) {
        if (klantDAO.vindKlantOpKlantId(klant.getTransactiepartijId()) == null) {
            return "Klant bestaat niet, update mislukt.";
        } else {
            accountDAO.updateAccount(klant);
            return "Update geslaagd";
        }
    }

    /**
     *
     * @return List<Account> geeft lijst van alle accounts uit DB terug
     */

    public List<Account> geefAlleAccounts() {
        return accountDAO.geefAlleAccounts();
    }

    /**
     *
     * @param klantId die gekoppeld is aan de account die gevonden moet worden
     * @return Account
     */

    public Account vindAccountOpKlantId(int klantId){
        return accountDAO.vindAccountOpKlantId(klantId);
    }

    /**
     *
     * @param emailadres die gekoppeld is aan de account die gevonden moet worden
     * @return Account
     */

    public Account vindAccountOpEmailadres(String emailadres) {return accountDAO.vindAccountOpEmailAdres(emailadres);}
}
