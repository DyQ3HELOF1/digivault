package nl.hva.c25.team1.digivault.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import nl.hva.c25.team1.digivault.transfer.RegisterDto;

import java.util.Objects;

// review door Erwin, 6 december

/**
 * @author Sezi, studentnummer 500889525
 *
 */

public class Account {
    private String emailadres;
    private String wachtwoord;

    @JsonBackReference
    private Klant klant;

    /**
     * All args constructor Account
     * @param emailadres
     * @param wachtwoord
     * @param klant
     */

    public Account (String emailadres, String wachtwoord, Klant klant) {
        super();
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
        this.klant = klant;
    }

    /**
     *
     * Constructor Account met kale klant object
     * @param emailadres
     * @param wachtwoord
     */

    public Account(String emailadres, String wachtwoord) {
        this(emailadres, wachtwoord, null);
    }

    /**
     *
     * Constructor Account met lege velden
     *
     */
    public Account() {
        this("onbekend", "onbekend");
    }

    /**
     *
     * Constructor Account voor in RegisterDto
     * @param dto voor het maken van Account object
     */

    public Account(RegisterDto dto){
        this(dto.getEmailadres(),dto.getWachtwoord());
    }


    public String getEmailadres() {
        return emailadres;
    }

    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", emailadres='" + emailadres + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(emailadres, account.emailadres) && Objects.equals(wachtwoord, account.wachtwoord) && Objects.equals(klant, account.klant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailadres, wachtwoord, klant);
    }
}

