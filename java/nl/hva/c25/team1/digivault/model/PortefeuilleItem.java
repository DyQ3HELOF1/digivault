// Created by antho
// Creation date 6-12-2021

package nl.hva.c25.team1.digivault.model;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 * 09-12-2021 review Anneke
 */
public class PortefeuilleItem {

    private int portefeuilleItemId;
    private double hoeveelheid;
    private TransactiePartij transactiePartij;
    private Asset asset;

    private PortefeuilleItem(int portefeuilleItemId, double hoeveelheid, TransactiePartij transactiePartij, Asset asset) {
        this.portefeuilleItemId = portefeuilleItemId;
        this.hoeveelheid = hoeveelheid;
        this.transactiePartij = transactiePartij;
        this.asset = asset;
    }

    public PortefeuilleItem(int portefeuilleItemId, double hoeveelheid) {
        this(portefeuilleItemId, hoeveelheid, null, null);
    }

    public PortefeuilleItem(int portefeuilleItemId) {
        this(portefeuilleItemId, 0);
    }

    public int getPortefeuilleItemId() {
        return portefeuilleItemId;
    }

    public void setPortefeuilleItemId(int portefeuilleItemId) {
        this.portefeuilleItemId = portefeuilleItemId;
    }

    public double getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(double hoeveelheid) {
        this.hoeveelheid = hoeveelheid;
    }

    public TransactiePartij getTransactiePartij() {
        return transactiePartij;
    }

    public void setTransactiePartij(TransactiePartij transactiePartij) {
        this.transactiePartij = transactiePartij;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Override
    public String toString() {
        return String.format("%-25s%+15.5f", asset, hoeveelheid);
    }

}