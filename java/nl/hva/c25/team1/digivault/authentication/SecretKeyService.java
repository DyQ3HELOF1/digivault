package nl.hva.c25.team1.digivault.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * Hoort bij Refresh token, maar vooralsnog wordt alleen de JWT gebruikt
 * @author Anneke
 */
@Service
public class SecretKeyService {

    private static final String SECRET = "ItsFunToHaveASecretAndKeepItVeryWell";

    @Autowired
    public SecretKeyService() {
    }

    public static String getSecret() {
        return SECRET;
    }


}
