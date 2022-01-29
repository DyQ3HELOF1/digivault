// Created by antho
// Creation date 23-12-2021

package nl.hva.c25.team1.digivault.transfer;

/**
 * @author Anthon
 */
public class TransactieDTO {

    private int koperId, verkoperId, assetId;

    private double aantal;

    public TransactieDTO(int koperId, int verkoperId, int assetId, double aantal) {
        this.koperId = koperId;
        this.verkoperId = verkoperId;
        this.assetId = assetId;
        this.aantal = aantal;
    }

    public int getKoperId() {
        return koperId;
    }

    public int getVerkoperId() {
        return verkoperId;
    }

    public double getAantal() {
        return aantal;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    @Override
    public String toString() {
        return String.format("%5d%5d%5d%15.5f", koperId, verkoperId, assetId, aantal);
    }

}