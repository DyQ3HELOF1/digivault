package nl.hva.c25.team1.digivault.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Nienke
 * @author Anthon
 */

public class Transactie {

    private int transactieId;
    private TransactiePartij koper, verkoper;
    private LocalDate transactieDatum;
    private LocalTime transactieTijd;
    private Asset asset;
    private double aantalCryptos;

    private Transactie(int transactieId, TransactiePartij koper, TransactiePartij verkoper, LocalDate transactieDatum,
                       LocalTime transactieTijd, Asset asset, double aantalCryptos) {
        this.transactieId = transactieId;
        this.koper = koper;
        this.verkoper = verkoper;
        this.transactieDatum = transactieDatum;
        this.transactieTijd = transactieTijd;
        this.asset = asset;
        setAantalCryptos(aantalCryptos);
    }

    public Transactie(LocalDate transactieDatum, LocalTime transactieTijd, double aantalCryptos){
        this(0, null, null, transactieDatum, transactieTijd, null, aantalCryptos);
    }

    public Transactie setTransactieId(int transactieId) {
        this.transactieId = transactieId;
        return this;
    }

    public TransactiePartij getKoper() {
        return koper;
    }

    public Transactie setKoper(TransactiePartij koper) {
        this.koper = koper;
        return this;
    }

    public TransactiePartij getVerkoper() {
        return verkoper;
    }

    public Transactie setVerkoper(TransactiePartij verkoper) {
        this.verkoper = verkoper;
        return this;
    }

    public LocalDate getTransactieDatum() {
        return transactieDatum;
    }

    public LocalTime getTransactieTijd() {
        return transactieTijd;
    }

    public Asset getAsset() {
        return asset;
    }

    public Transactie setAsset(Asset asset) {
        this.asset = asset;
        return this;
    }

    public double getAantalCryptos() {
        return aantalCryptos;
    }

    public Transactie setAantalCryptos(double aantalCryptos) throws IllegalArgumentException {
        if (aantalCryptos > 0) {
            this.aantalCryptos = aantalCryptos;
        } else {
            throw new IllegalArgumentException("Aantal cryptomunten moet een positief getal zijn!");
        }
        return this;
    }

    @Override
    public String toString() {
        return "TransactieId = " + transactieId; // tijdelijk aangepast ivm testing
    }

}