// Created by antho
// Creation date 7-12-2021

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 * 09-12-2021 review Anneke
 */
@Service
public class PortefeuilleItemService {

    private RootRepository rootRepository;

    @Autowired
    public PortefeuilleItemService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }

    /**
     * Geeft portefeuilleitem met bepaald id.
     *
     * @param itemId Het id van het portefeuilleitem.
     * @return Het portefeuilleitem.
     */
    public PortefeuilleItem vindItemMetId(int itemId) {
        return rootRepository.vindItemOpId(itemId);
    }

}