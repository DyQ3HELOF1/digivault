package nl.hva.c25.team1.digivault.repository;


import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;

import java.util.List;

/**
 * Interface met de te implementeren methodes voor JdbcNaamDAO
 *
 * @author Anneke, studentnummer 500889251
 * @since 3-12-2021
 */

public interface NaamDAO {

    Naam vindNaamOpKlantId(int klantId);

    List<Naam> vindAlleNamen();

    void update(Klant klant);



}
