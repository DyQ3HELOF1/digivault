package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;

import java.util.List;

/**
 * Interface met de te implementeren methodes voor JdbcAdresDAO
 *
 * @author Anneke, studentnummer 500889251
 * @since 3-12-2021
 */

public interface AdresDAO {

    Adres bewaarAdresMetSK(Adres adres);

    Adres vindAdresOpAdresId(int adresId);

    List<Adres> vindAlleAdressen();

    void update(Adres adres);
}
