package nl.hva.c25.team1.digivault.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 14-12-2021
 *
 */

@Service
public class TokenService {
    private SecretKeyService secretKeyService;

    @Autowired
    public TokenService(SecretKeyService secretKeyService) {
        this.secretKeyService = secretKeyService;
    }

    private String token;
    private final String KEY = SecretKeyService.getSecret();
    private Algorithm algorithm = Algorithm.HMAC256(KEY);
    private final String ISSUER = "Digivault";
    private final String AUDIENCE = "https://www.digivault.com";
    private Map<String, Object> headerClaims = new HashMap<>();
    private final String ALG = "HS256";
    private final String TYP = "JWT";
    private Instant now;


    /**
     *
     * Methode die wordt aangeroepen vanuit de LoginService om JWT token te maken
     * @param emailadres van klant die probeert in te loggen
     * @return JWT token (String)
     */
    public String maakJWT(String emailadres) {
        try {
            token = vulToken(emailadres);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error tijdens het maken van een token voor: " + emailadres);
        }
        return token;
    }

    /**
     *
     * In deze methode wordt de JWT samengesteld
     * Hij krijgt een issuedatumtijd en vervaldatumtijd mee
     * @param emailadres van klant die probeert in te loggen
     * @return een volledig JWT token die na 15 min. vervalt
     */
    public String vulToken(String emailadres){
        now = Instant.now();
        headerClaims.put("alg", ALG);
        headerClaims.put("typ", TYP);
        token = JWT.create()
                .withIssuer(ISSUER)
                .withAudience(AUDIENCE)
                .withClaim("emailadres", emailadres)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(15 ,ChronoUnit.MINUTES)))
                .withHeader(headerClaims)
                .sign(algorithm);
        return token;
    }

    /**
     *
     * Deze methode wordt aangeroepen vanuit de controllers van te openen pagina's
     * Hier wordt de token geverifieerd en gecheckt of de vervaldatumtijd niet verstreken is
     *     * @param token
     *      * @return boolean (is token valide ja/nee)
     */
    public boolean valideerJWT(String token){
        try {
            JWTVerifier verifier = bouwVerifier();
            DecodedJWT jwt = verifier.verify(token);
            Date expirationTime = jwt.getExpiresAt();
            Date now = Date.from(Instant.now());
            // check of de 15 min. zijn verstreken, zo nee: true, zo ja: false
            return expirationTime == null || !now.after(expirationTime);
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    /**
     *
     * In deze methode wordt bepaald waarop de JWT gevalideerd gaat worden
     * In dit geval op issuer en audience en uiteraard op algorithm
     * @return JWTVerifier met aantal checks
     */
    public JWTVerifier bouwVerifier(){
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .withAudience(AUDIENCE)
                .build();
    }

    /**
     *
     * Deze methode wordt aangeroepen vanuit de controllers van te openen pagina's
     * De methode geeft het emailadres terug dat meegenomen is in de payload van de JWT
     * en kan zo vergeleken worden met het emailadres van de gebruiker
     * @param token van gebruiker
     * @return emailadres uit token
     */
    public String getEmailadresToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("emailadres").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}

