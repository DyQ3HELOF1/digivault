package nl.hva.c25.team1.digivault.model;

// review door Erwin, 1 december

import java.util.Objects;

/**
 * @author Sezi, studentnummer 500889525
 * @version 4-12-2021
 */

public class Rekening {
    private int rekeningId;
    private String IBAN;
    private double saldo;

    /**
     * All args constructor Rekening
     * @param rekeningId
     * @param IBAN
     * @param saldo
     */

    public Rekening(int rekeningId, String IBAN, double saldo) {
        super();
        this.rekeningId = rekeningId;
        this.IBAN = IBAN;
        this.saldo = saldo;
    }

    /**
     *
     * Constructor Rekening zonder rekeningId
     * @param IBAN
     * @param saldo
     */

    public Rekening (String IBAN, double saldo){
        this(0, IBAN,saldo);
    }

    /**
     *
     * Constructor Rekening zonder saldo
     * @param rekeningId
     * @param IBAN
     */

    public Rekening (int rekeningId, String IBAN) {
        this(rekeningId, IBAN, 0);
    }

    /**
     *
     * Constructor kale Rekening object
     *
     */

    public Rekening() { this(0, "onbekend", 0);}

    /**
     *
     * Constructor met alleen rekeningId
     * @param rekeningId
     *
     */

    public Rekening(int rekeningId) { this(rekeningId, "");}

    public int getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(int rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rekening rekening = (Rekening) o;
        return rekeningId == rekening.rekeningId && Double.compare(rekening.saldo, saldo) == 0 && IBAN.equals(rekening.IBAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rekeningId, IBAN, saldo);
    }

    public String toString() {
        return String.format("Rekeningnummer %s met saldo %.2f", this.IBAN, this.saldo);
    }
}
