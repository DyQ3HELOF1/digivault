package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Adres;
import nl.hva.c25.team1.digivault.model.Klant;
import nl.hva.c25.team1.digivault.model.Naam;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author Nienke en Erwin (validatie)
 * @version 8-12-2021
 */

public class RegisterDto {
    @Email(message = "Vul een geldig e-mailadres in")
    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Vul een geldig e-mailadres in")
    @NotEmpty(message = "Vul een e-mailadres in")
    private String emailadres;
    @Size(min = 10, message = "Het wachtwoord moet uit minimaal 10 tekens bestaan")
    @Size(max = 60, message = "Het wachtwoord moet uit minimaal 60 tekens bestaan")
    @Pattern(regexp = "[^ ]+", message = "Gebruik van spaties in wachtwoord is niet toegestaan")
    @NotEmpty(message = "Vul een wachtwoord in")
    private String wachtwoord;
    @Pattern(regexp = "[a-zA-Z ]+", message = "Vul een geldige voornaam in")
    @NotEmpty(message = "Vul een voornaam in")
    private String voornaam;
    @Size(max = 15, message = "Vul een geldig tussenvoegsel in")
    private String tussenvoegsel;
    @Pattern(regexp = "[a-zA-Z ]+", message = "Vul een geldige achternaam in")
    @NotEmpty(message = "Vul een achternaam in")
    private String achternaam;
    @NotNull(message = "De geboortedatum mag niet leeg zijn")
    private LocalDate geboortedatum;
    @Size(min = 8, max = 9, message = "Een geldig bsn-nummer bestaat uit 8 of 9 cijfers")
    @Pattern(regexp = "[0-9]+", message = "Vul een geldige bsn-nummer in")
    @NotEmpty(message = "Het bsn-nummer mag niet leeg zijn")
    private String bsn;
    @Pattern(regexp = "[a-zA-Z ]+", message = "Vul een geldige straatnaam in")
    @NotEmpty(message = "Vul een straat in")
    private String straat;
    @Min(value = 0, message = "Vul een geldig huisnummer in")
    @NotNull(message = "Vul een huisnummer in")
    private int huisnummer;
    @Size(max = 5, message = "Vul een geldige toevoeging in")
    private String toevoeging;
    @Pattern(regexp = "^[1-9][0-9]{3}[A-Za-z]{2}", message = "Vul een geldige postcode in")
    @NotEmpty(message = "Vul een postcode in")
    private String postcode;
    @Pattern(regexp = "[a-zA-Z ]+", message = "Vul een geldige woonplaats in")
    @NotEmpty(message = "Vul een woonplaats in")
    private String woonplaats;

    public RegisterDto(Klant klant, Naam naam, Adres adres, Account account){
        this.emailadres = account.getEmailadres();
        this.wachtwoord = account.getWachtwoord();
        this.voornaam = naam.getVoornaam();
        this.tussenvoegsel = naam.getTussenvoegsel();
        this.achternaam = naam.getAchternaam();
        this.geboortedatum = klant.getGeboortedatum();
        this.bsn = klant.getBsn();
        this.huisnummer = adres.getHuisnummer();
        this.toevoeging = adres.getToevoeging();
        this.postcode = adres.getPostcode();
    }

    public RegisterDto(){

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

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
}
