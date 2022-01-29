package nl.hva.c25.team1.digivault.model;

// review door Sezi, 6 december

/**
 *
 * @author Erwin, studentnummer 500889293
 * @since 30-11-2021
 */

public class Asset {
    private int assetId;
    private String afkorting;
    private String naam;
    private double dagKoers;

    /**
     * Constructor van de klasse Asset
     * @param assetId id van de Asset
     * @param afkorting afkorting van de Asset
     * @param naam naam van de Asset
     * @param dagKoers de huidige waarde van een asset
     */
    public Asset(int assetId, String afkorting, String naam, double dagKoers) {
        super();
        setAssetId(assetId);
        this.afkorting = afkorting;
        this.naam = naam;
        this.dagKoers = dagKoers;
    }

    /**
     * Constructor van de klasse Asset met lege dagkoers
     * @param assetId id van de Asset
     * @param afkorting afkorting van de Asset
     * @param naam naam van de Asset
     */
    public Asset(int assetId, String afkorting, String naam) {
        this(assetId, afkorting, naam, 0.0);
    }

    /**
     * Kale constructor van klasse Asset met alleen assetId
     * @param assetId id van de Asset
     */
    public Asset(int assetId) {
        this(assetId, "", "");
    }

    /**
     * Lege constructor van klasse Asset
     */
    public Asset() {
        this(0, "", "");
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) throws IllegalArgumentException {
        if (assetId > 0) {
            this.assetId = assetId;
        } else {
            throw new IllegalArgumentException("Het ID van een asset moet een positief geheel getal zijn!");
        }
    }

    public String getAfkorting() {
        return afkorting;
    }

    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getDagKoers() {
        return dagKoers;
    }

    public void setDagKoers(double dagKoers) {
        this.dagKoers = dagKoers;
    }

    /**
     * Equals-methode Asset
     * @param other te vergelijken Asset
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Asset asset = (Asset) other;
        return assetId == asset.assetId;
    }

    /**
     * HashCode Asset
     * @return Hash assetId
     */
    @Override
    public int hashCode() {
        return assetId;
    }

    /**
     * toString-methode Asset
     * @return toString van klasse Asset
     */
    @Override
    public String toString() {
        return "Asset{" +
                "assetId=" + assetId +
                ", afkorting='" + afkorting + '\'' +
                ", naam='" + naam + '\'' +
                ", dagKoers=" + dagKoers +
                '}';
    }
}
