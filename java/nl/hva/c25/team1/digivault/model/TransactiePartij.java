package nl.hva.c25.team1.digivault.model;

import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public abstract class TransactiePartij {
    private int transactiepartijId;
    private Rekening rekening;
    private List<PortefeuilleItem> portefeuille;

    protected TransactiePartij(int transactiepartijId, Rekening rekening, List<PortefeuilleItem> portefeuille) {
        setTransactiepartijId(transactiepartijId);
        this.rekening = rekening;
        this.portefeuille = portefeuille;
    }

    public int getTransactiepartijId() {
        return transactiepartijId;
    }

    public void setTransactiepartijId(int transactiepartijId) throws IllegalArgumentException {
        if (transactiepartijId >= 0) {
            this.transactiepartijId = transactiepartijId;
        } else {
            throw new IllegalArgumentException("Het ID van een transactiepartij moet een positief geheel getal zijn!");
        }
    }

    public Rekening getRekening() {
        return rekening;
    }

    public void setRekening(Rekening rekening) {
        this.rekening = rekening;
    }

    public List<PortefeuilleItem> getPortefeuille() {
        return portefeuille;
    }

    public void setPortefeuille(List<PortefeuilleItem> portefeuille) {
        this.portefeuille = portefeuille;
    }
}
