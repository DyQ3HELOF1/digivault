package nl.hva.c25.team1.digivault.auth_nietgebruikt;

import nl.hva.c25.team1.digivault.model.Klant;

import java.util.UUID;

/**
 *
 * Refreshtoken + klant, wordt vooralsnog niet gebruikt
 * @author Anneke, studentnummer 500889251
 * @since 15-12-2021
 *
 */

public class TokenKlantPaar {

    private UUID key;
    private Klant klant;

    public TokenKlantPaar(UUID key, Klant klant) {
        super();
        this.key = key;
        this.klant = klant;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
}
