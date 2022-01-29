package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Transactie;

/**
 * @author Nienke
 * @author Anthon
 */
public interface TransactieDAO {

    Transactie bewaarTransacktieMetSK(Transactie transactie);

    Transactie vindTransactieOpTransactieId(int transactieId);

}

