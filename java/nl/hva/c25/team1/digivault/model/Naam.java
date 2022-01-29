package nl.hva.c25.team1.digivault.model;

import nl.hva.c25.team1.digivault.transfer.RegisterDto;

import java.util.Objects;

/**
 *
 * @author Anneke, studentnummer 500889251
 * @since 1-12-2021
 *
 * Review Anthon 8-12-2021
 */

public class Naam {
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;

    /**
     *
     * All args constructor Naam
     * @param voornaam van klant
     * @param tussenvoegsel van klant
     * @param achternaam van klant
     */
    public Naam(String voornaam, String tussenvoegsel, String achternaam) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
    }

    /**
     *
     * Constructor Naam voor in RegisterDto
     * @param dto voor registratie klant
     */
    public Naam(RegisterDto dto) {
        this(dto.getVoornaam(), dto.getTussenvoegsel(), dto.getAchternaam());
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

    @Override
    public String toString() {
        return String.format("%s %s %s", voornaam, tussenvoegsel, achternaam);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Naam naam = (Naam) o;
        return Objects.equals(voornaam, naam.voornaam) && Objects.equals(tussenvoegsel, naam.tussenvoegsel) && Objects.equals(achternaam, naam.achternaam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voornaam, tussenvoegsel, achternaam);
    }
}
