package nl.hva.c25.team1.digivault.model;

import java.util.List;

/**
 * Author Nienke
 * Version 14-12-2021
 */

public class Bank extends TransactiePartij {
    private String bankNaam;
    private double transactiePercentage;

    private Bank(int bankId, Rekening rekening, List<PortefeuilleItem> portefeuille, String bankNaam,
                double transactiePercentage) {
        super(bankId, rekening, portefeuille);
        this.bankNaam = bankNaam;
        this.transactiePercentage = transactiePercentage;
    }

    public Bank(int bankId, String bankNaam, double transactiePercentage) {
        this(bankId, null, null, bankNaam, transactiePercentage);
    }

    public Bank(String bankNaam, double transactiePercentage) {
        this(0, bankNaam, transactiePercentage);
    }

    public Bank(int bankId) {
        this(bankId, "", 0);
    }

    public String getBankNaam() {
        return bankNaam;
    }

    public void setBankNaam(String bankNaam) {
        this.bankNaam = bankNaam;
    }

    public double getTransactiePercentage() {
        return transactiePercentage;
    }

    public void setTransactiePercentage(double transactiePercentage) {
        this.transactiePercentage = transactiePercentage;
    }

    @Override
    public String toString() {
        return "Bank{" +
                ", bankNaam='" + bankNaam + '\'' +
                ", transactiePercentage=" + transactiePercentage +
                '}';
    }

}
