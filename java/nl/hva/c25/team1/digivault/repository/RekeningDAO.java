package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Rekening;

import java.util.List;

// review door Erwin, 1 december

/**
 * Interface met de te implementeren methodes voor JdbcRekeningDAO
 *
 * @author Sezi, studentnummer 500889525
 *
 */

public interface RekeningDAO {

    void bewaar(Rekening rekening);

    void bewaarRekeningMetSK(Rekening rekening);

    void updateRekening(Rekening rekening);

    Rekening vindRekeningOpIBAN(String IBAN);

    Rekening vindRekeningOpId(int rekeningId);

    List<Rekening> geefAlleRekeningen();

    Rekening vindRekeningOpTransactiePartijId(int transactiepartijId);
}
