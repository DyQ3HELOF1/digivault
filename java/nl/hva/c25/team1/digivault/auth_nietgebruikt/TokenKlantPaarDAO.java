package nl.hva.c25.team1.digivault.auth_nietgebruikt;

import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.auth_nietgebruikt.TokenKlantPaar;

import java.util.Optional;
import java.util.UUID;

/**
 *
 * Wordt vooralsnog niet gebruikt
 * @author Anneke, studentnummer 500889251
 * @since 15-12-2021
 *
 */


public interface TokenKlantPaarDAO {

    Optional<TokenKlantPaar> vindPaarOpKlant(Klant klant);
    void save(TokenKlantPaar tokenKlantPaar);

    Optional<TokenKlantPaar> vindOpKey(UUID key);

    void delete(UUID uuid);





}
