package nl.hva.c25.team1.digivault.model;

import nl.hva.c25.team1.digivault.transfer.RegisterDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 *
 * Review Anthon 6-12-2021
 */

public class Klant extends TransactiePartij {
    private String bsn;
    private LocalDate geboortedatum;
    private Naam naam;
    private Adres adres;
    private Account account;

    /**
     * All args constructor Klant
     * @param klantId van klant
     * @param bsn van klant
     * @param geboortedatum van klant
     * @param naam van klant
     * @param adres van klant
     * @param account van klant
     * @param rekening van klant
     * @param portefeuille van klant
     */
    private Klant(int klantId, String bsn, LocalDate geboortedatum, Naam naam, Adres adres,
                 Account account, Rekening rekening, List<PortefeuilleItem> portefeuille) {
        super(klantId,rekening, portefeuille);
        this.bsn = bsn;
        this.geboortedatum = geboortedatum;
        this.naam = naam;
        this.adres = adres;
        this.account = account;
    }

    /**
     *
     * Constructor Klant zonder klantId
     * @param bsn van klant
     * @param geboortedatum van klant
     * @param naam van klant
     * @param adres van klant
     * @param account van klant
     */
    public Klant(String bsn, LocalDate geboortedatum, Naam naam, Adres adres, Account account) {
        this(0, bsn, geboortedatum, naam, adres, account, null, null);
    }

    /**
     *
     * Constructor Klant voor in RegisterDto
     * @param dto voor registreren klant
     */
    public Klant(RegisterDto dto){
        this(dto.getBsn(), dto.getGeboortedatum(), new Naam(dto), new Adres(dto), new Account(dto) );
    }

    /**
     *
     * Kale Klant constructor
     * @param klantId van klant
     * @param bsn van klant
     * @param geboortedatum van klant
     */
    public Klant(int klantId, String bsn, LocalDate geboortedatum) {
        this(klantId,bsn,geboortedatum, null, null,null, null, null);
    }

    public Klant(String bsn, LocalDate geboortedatum) {
        this(0, bsn, geboortedatum, null, null, null, null, null);
    }

    /**
     *
     * Constructor met alleen klantId, wordt gebruikt om lege portefeuille aan klant te koppelen
     * @param klantId van klant
     */
    public Klant(int klantId) {
        this(klantId, "", null);
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Naam getNaam() {
        return naam;
    }

    public void setNaam(Naam naam) {
        this.naam = naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Klant{ bsn='" + bsn + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", naam=" + naam +
                ", adres=" + adres +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klant klant = (Klant) o;
        return Objects.equals(bsn, klant.bsn) && Objects.equals(geboortedatum, klant.geboortedatum) && Objects.equals(naam, klant.naam) && Objects.equals(adres, klant.adres) && Objects.equals(account, klant.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bsn, geboortedatum, naam, adres, account);
    }
}
