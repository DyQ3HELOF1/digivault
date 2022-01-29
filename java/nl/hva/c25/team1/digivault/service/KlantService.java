package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.JdbcKlantDAO;
import nl.hva.c25.team1.digivault.repository.KlantDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

/**
 * Service van de klasse Klant
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 */

@Service
public class KlantService {

    private KlantDAO klantDAO;
    private RootRepository rootRepository;

    @Autowired
    public KlantService(JdbcKlantDAO klantDAO, RootRepository rootRepository) {
        this.klantDAO = klantDAO;
        this.rootRepository = rootRepository;
    }

    /**
     *
     * @param klant die wordt opgeslagen
     */
    public Klant bewaarKlant(Klant klant) {
        return klantDAO.bewaarKlantMetSK(klant);
    }

    /**
     *
     * @param klantId van klant die gevonden moet worden
     * @return Klant
     */
    public Klant vindKlantOpKlantID(int klantId) {
        return rootRepository.vindKlantOpId(klantId);
    }

    public Klant vindKlantOpEmail(String emailadres) {
        return klantDAO.vindKlantOpEmailadres(emailadres);
    }

    /**
     *
     * @return List<Klant> klantenlijst
     */
    public List<Klant> vindAlleKlanten() {
        return klantDAO.vindAlleKlanten();
    }

    /**
     *
     * update een bestaande klant uit de database

     * @param klant die geupdate moet worden
     * @return String melding die aangeeft of update geslaagd is
     */
    public String updateKlant(Klant klant) {
        if (klantDAO.vindKlantOpKlantId(klant.getTransactiepartijId()) == null ) {
            return "Klant bestaat niet, update mislukt.";
        } else {
            klantDAO.update(klant);
            return "Update geslaagd";
        }
    }
}
