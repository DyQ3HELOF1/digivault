package nl.hva.c25.team1.digivault.service;

import java.math.BigInteger;
import java.util.Random;

/**
 * Service die volgens de 11-proef DIVA-IBAN-nummers genereert
 *
 * @author Erwin, studentnummer 500889293
 * @since 8-12-2021
 */

public class IbanService {
    public static String LANDCODE = "NL";
    public static String BANKCODE = "DIVA";
    public static String CONTROLEREEKS_LANDCODE = "2321";
    public static String CONTROLEREEKS_BANKCODE = "13183110";
    public static String CONTROLEREEKS_SLOT = "00";
    public static String NULWAARDE = "0";
    public static int ASCII_MINIMUM = 48;      // ASCII-waarden vanaf cijfer 0
    public static int ASCII_MAXIMUM = 57;      // ASCII-waarden tot en met cijfer 9
    public static int LENGTE_REKENINGNUMMER = 10;    // gewenste lengte van bankrekeningNummer
    public static int LENGTE_CONTROLEGETAL = 2;      // aantal tekens waar een controlegetal uit moet bestaan
    public static BigInteger MODULO = BigInteger.valueOf(97);
    public static BigInteger BRUTO = BigInteger.valueOf(98);

    /**
     * Constructor van de IbanService
     */
    public IbanService() {
        super();
    }

    /**
     * Genereert een IBAN die voldoet aan de 11-proef; Eerst wordt een random bankrekeningnummer van 10 cijfers
     * gegenereerd, hier worden vervolgens de landcode, het gegenereerde controlegetal en de bankcode aan toegevoegd
     * @return de gegenereerde IBAN als String
     */
    public static String IbanGenerator() {
        String bankrekeningNummer = genereerRandomBankrekeningNummer();
        String controlegetal = genereerControleGetal(bankrekeningNummer);
        return LANDCODE + controlegetal + BANKCODE + bankrekeningNummer;
    }

    /**
     * Genereert een random bankrekeningNummer van 10 cijfers als String
     * @return random gegenereerde bankrekeningNummer als String
     */
    public static String genereerRandomBankrekeningNummer() {
        Random random = new Random();
        return random.ints(ASCII_MINIMUM, ASCII_MAXIMUM + 1)
                .limit(LENGTE_REKENINGNUMMER)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Deze methode voegt een gegenereerd bankrekeningnummer samen met de numerieke waarden van bankcode, de landcode
     * en twee nullen aan het eind. Deze zo gevormde controleReeks is een String, die wordt omgezet naar een
     * BigInteger om de bijbehorende modulo 97 berekening te kunnen doen en dit af te trekken van 98 (= bruto). De
     * uitkomst (=controlegetal) wordt als String teruggegeven, en indien nodig via de methode vulControleGetalAan
     * aangevuld tot twee tekens
     * @param bankrekeningNummer het gegenereerde bankrekeningnummer waarvoor een controlegetal berekend wordt
     * @return controlegetal als String
     */
    public static String genereerControleGetal(String bankrekeningNummer) {
        String controleReeksAlsString = CONTROLEREEKS_BANKCODE + bankrekeningNummer + CONTROLEREEKS_LANDCODE
                + CONTROLEREEKS_SLOT;
        BigInteger controleReeksAlsLong = new BigInteger(controleReeksAlsString);
        BigInteger moduloReeks = controleReeksAlsLong.mod(MODULO);
        String controleGetal = BRUTO.subtract(moduloReeks).toString();
        return vulControleGetalAan(controleGetal);
    }

    /**
     * Methode vult de controlegetallen die uit één teken bestaan aan tot twee tekens door er een nul aan toe te voegen
     * @param controleGetal het controlegetal dat mogelijk aangevuld moet worden als String
     * @return het correcte en eventueel aangevulde controlegetal als String
     */
    public static String vulControleGetalAan(String controleGetal) {
        if (controleGetal.length() == LENGTE_CONTROLEGETAL) {
            return controleGetal;
        }
        return NULWAARDE + controleGetal;
    }
}
