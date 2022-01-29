package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.model.EuroKoers;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface met de te implementeren methodes voor JdbcAssetDAO
 *
 * @author Erwin, studentnummer 500889293
 * @since 2-12-2021
 */

public interface EuroKoersDAO {

    EuroKoers bewaarEuroKoersMetSK(EuroKoers euroKoers);

    EuroKoers vindEuroKoersOpId(int euroKoersId);

    List<EuroKoers> geefAlleEuroKoersen();

    void update(EuroKoers euroKoers);

    EuroKoers vindMeestRecenteKoersAsset(Asset asset);

}
