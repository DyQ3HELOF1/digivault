// Created by antho
// Creation date 2-1-2022

package nl.hva.c25.team1.digivault.transfer;

import nl.hva.c25.team1.digivault.model.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deze klasse is bedoeld voor het omzetten van een TransactieDTO naar een Transactie-object.
 *
 * @author Anthon
 */
public class TransactieMapper {

    private static final int LOWEST_ID_CUSTOMER = 10; // id 1-9 zijn banken; hoger zijn klanten

    public TransactieMapper() {
        super();
    }

    /**
     * Deze klasse zorgt voor het omzetten van een TransactieDTO naar een Transactie-object.
     *
     * @param transactieDTO De DTO die omgezet moet worden.
     * @return De transactie geïnitialiseerd met huidige datum en tijd, aantal cryptomunten, assetId, koperId en
     * verkoperId.
     */
    public Transactie toObject(TransactieDTO transactieDTO) {
        Transactie transactie = new Transactie(LocalDate.now(), LocalTime.now(), transactieDTO.getAantal());
        transactie.setAsset(new Asset(transactieDTO.getAssetId()));
        setKoper(transactie, transactieDTO.getKoperId());
        setVerkoper(transactie, transactieDTO.getVerkoperId());
        if (transactie.getKoper() instanceof Bank && transactie.getVerkoper() instanceof Bank) { // beide bank
            return null;
        }
        return transactie;
    }

    // Deze methode initialiseert de koper met de juiste id in de meegegeven transactie.
    void setKoper(Transactie transactie, int koperId) {
        if (koperId < LOWEST_ID_CUSTOMER) { // koper is bank
            transactie.setKoper(new Bank(koperId));
        } else { // koper is klant
            transactie.setKoper(new Klant(koperId));
        }
    }

    // Deze methode initialiseert de verkoper met de juiste id in de meegegeven transactie.
    void setVerkoper(Transactie transactie, int verkoperId) {
        if (verkoperId < LOWEST_ID_CUSTOMER) { // verkoper is bank
            transactie.setVerkoper(new Bank(verkoperId));
        } else { // verkoper is klant
            transactie.setVerkoper(new Klant(verkoperId));
        }
    }

}