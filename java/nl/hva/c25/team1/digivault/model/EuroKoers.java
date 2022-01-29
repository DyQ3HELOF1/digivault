package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;

/**
 * @author Erwin, studentnummer 500889293
 * @since 2-12-2021
 */

public class EuroKoers {
    private int euroKoersId;
    private LocalDate datum;
    private double koers;
    private int assetId;

    /**
     * Constructor van de klasse EuroKoers
     * @param euroKoersId id van de EuroKoers
     * @param datum datum bij EuroKoers
     * @param koers bijbehorende koers
     * @param assetId de bijbehorende assetId
     */
    private EuroKoers(int euroKoersId, LocalDate datum, double koers, int assetId) {
        super();
        this.euroKoersId = euroKoersId;
        this.datum = datum;
        this.koers = koers;
        this.assetId = assetId;
    }

    /**
     * Kale constructor van de klasse EuroKoers zonder assetId
     * @param euroKoersId id van de EuroKoers
     * @param datum datum bij EuroKoers
     * @param koers bijbehorende koers
     */
    public EuroKoers(int euroKoersId, LocalDate datum, double koers) {
        this(euroKoersId, datum, koers, 0);
    }

    /**
     * Kale constructor van klasse Asset met alleen euroKoersId
     * @param euroKoersId id van de EuroKoers
     */
    public EuroKoers(int euroKoersId) {
        this(euroKoersId, null, 0);
    }

    /**
     * Lege constructor van de klasse EuroKoers
     */
    public EuroKoers() {
        this(0,null, 0.0);
    }

    public int getEuroKoersId() {
        return euroKoersId;
    }

    public void setEuroKoersId(int euroKoersId) throws IllegalArgumentException {
        if (euroKoersId > 0) {
            this.euroKoersId = euroKoersId;
        } else {
            throw new IllegalArgumentException("Het ID van een asset moet een positief geheel getal zijn!");
        }
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getKoers() {
        return koers;
    }

    public void setKoers(double koers) {
        this.koers = koers;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    /**
     * Equals-methode EuroKoers
     * @param other te vergelijken EuroKoers
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        EuroKoers euroKoers = (EuroKoers) other;
        return euroKoersId == euroKoers.euroKoersId;
    }

    /**
     * HashCode EuroKoers
     * @return Hash euroKoersId
     */
    @Override
    public int hashCode() {
        return euroKoersId;
    }

    /**
     * toString-methode EuroKoers
     * @return toString van klasse EuroKoers
     */
    @Override
    public String toString() {
        return "EuroKoers{" +
                "euroKoersId=" + euroKoersId +
                ", datum=" + datum +
                ", koers=" + koers +
                ", assetId=" + assetId +
                '}';
    }
}
