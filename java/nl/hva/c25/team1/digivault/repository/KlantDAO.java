package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Klant;

import java.util.List;

/**
 * Interface met de te implementeren methodes voor JdbcKlantDAO
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 *
 * Review Anthon 6-12-2021
 */

public interface KlantDAO {

    Klant bewaarKlantMetSK(Klant klant);

    Klant vindKlantOpKlantId(int klantId);

    Klant vindKlantOpEmailadres(String email);

    List<Klant> vindAlleKlanten();

    void update(Klant klant);

}
