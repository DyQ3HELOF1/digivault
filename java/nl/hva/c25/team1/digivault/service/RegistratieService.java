package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.authentication.HashService;
import nl.hva.c25.team1.digivault.model.*;
import nl.hva.c25.team1.digivault.repository.JdbcAccountDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service voor het registreren van een Klant
 *
 * @author Erwin, studentnummer 500889293
 * @since 8-12-2021
 */

@Service
public class RegistratieService {
    private final RootRepository rootRepository;
    private final AssetService assetService;
    private final HashService hashService;
    private final JdbcAccountDAO accountDAO;

    public static int BEGINSALDO = 100000;      // Saldo dat een nieuwe klant meekrijgt op zijn rekening
    public static int MINIMUM_LEEFTIJD = 18;
    public static int MAXIMUM_LEEFTIJD = 121;

    /**
     * Constructor van de RegistratieService
     * @param rootRepository RootRepository
     * @param assetService AssetService
     * @param hashService HashService
     */
    @Autowired
    public RegistratieService(RootRepository rootRepository, AssetService assetService, HashService hashService,
                              JdbcAccountDAO accountDAO) {
        super();
        this.rootRepository = rootRepository;
        this.assetService = assetService;
        this.hashService = hashService;
        this.accountDAO = accountDAO;
    }

    /**
     * Methodes voor het registreren van een klant, de klant krijgt een goed gevulde rekening en lege portefeuille mee:
     * een lijst van 20 assets die op 0 zijn gezet
     *
     * uitgesplitst in registatie en validatieKlant
     *
     * @author Anneke en Erwin
     *
     */
    public void registratie(Klant klant) {
        try {
            Rekening rekening = new Rekening(0, genereerIban());
            rekening.setSaldo(BEGINSALDO);
            klant.setRekening(rekening);
            klant.setPortefeuille(aanmaakLegePortefeuille());
            Account account = klant.getAccount();
            account.setWachtwoord(hashService.hash(account.getWachtwoord()));
            valideerKlant(klant);
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * @param klant Klant die geregistreerd moet worden
     * @return de geregistreerde Klant
     */
    public Klant valideerKlant(Klant klant){
        boolean validatie1 = validateBsn(klant.getBsn());
        boolean validatie2 = validatieGeboortedatum(klant.getGeboortedatum());
        boolean validatie3 = validatieMailadres(klant.getAccount().getEmailadres());
        if (validatie1 && validatie2 && validatie3) {
            return rootRepository.slaKlantOp(klant);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Methode die een lege portefeuille aanmaakt: een lijst van 20 assets die op 0 zijn gezet
     * @return de aangemaakte lege portefeuillelijst
     */
    public List<PortefeuilleItem> aanmaakLegePortefeuille() {
        List<PortefeuilleItem> legePortefeuille = new ArrayList<>();
        for (Asset asset: assetService.geefAlleAssets()) {
            PortefeuilleItem portefeuilleItem = new PortefeuilleItem(0);
            portefeuilleItem.setAsset(asset);
            portefeuilleItem.setTransactiePartij(new Klant(0));
            legePortefeuille.add(portefeuilleItem);
        }
        return legePortefeuille;
    }

    /**
     * Methode die doorverwijst naar de IbanService voor het aanmaken van een nieuwe DIVA-Iban
     * @return gegenereerde IBAN als String
     */
    public String genereerIban() {
        return IbanService.IbanGenerator();
    }

    /**
     * methode om te checken of de ingevoerde BSN-nummer correct is
     * @param bsn het ingevoerde BSN-nummer
     */
    public boolean validateBsn(String bsn) {
        int bsnInt = Integer.parseInt(bsn);

        if (bsnInt <= 9999999 || bsnInt > 999999999) {
            return false;
        }
        return doeElfProef(bsnInt);
    }

    /**
     * Voert elfproef uit op ingevoerde BSN-nummer
     * @param bsnInt ingevoerde BSN-nummer
     * @return resultaat elfproef
     */
    public boolean doeElfProef(int bsnInt) {
        int sum = -1 * bsnInt % 10;

        for (int multiplier = 2; bsnInt > 0; multiplier++) {
            int val = (bsnInt /= 10) % 10;
            sum += multiplier * val;
        }
        return sum != 0 && sum % 11 == 0;
    }

    /**
     * Methode om te checken of de klant tussen de 18 en 121 jaar is
     * @param geboortedatum ingevoerde geboortedatum
     */
    public boolean validatieGeboortedatum (LocalDate geboortedatum) {
        int leeftijd = Period.between(geboortedatum, LocalDate.now()).getYears();
        return leeftijd < MAXIMUM_LEEFTIJD && leeftijd > MINIMUM_LEEFTIJD;
    }

    /**
     * Check of mailadres al in database zit
     * @param emailadres te checken mailadres
     * @return boolean
     */
    public boolean validatieMailadres(String emailadres) {
        for (Account account : accountDAO.geefAlleAccounts()) {
            if (Objects.equals(account.getEmailadres(), emailadres)) {
                return false;
            }
        }
        return true;
    }
}
