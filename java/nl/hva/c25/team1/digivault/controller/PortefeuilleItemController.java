// Created by antho
// Creation date 1-12-2021

package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.PortefeuilleItem;
import nl.hva.c25.team1.digivault.service.PortefeuilleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 9-12-2021 review Anneke

@RestController
public class PortefeuilleItemController {

    private PortefeuilleItemService portefeuilleItemService;

    @Autowired
    public PortefeuilleItemController(PortefeuilleItemService portefeuilleItemService) {
        this.portefeuilleItemService = portefeuilleItemService;
    }

    @GetMapping("/portefeuilleitems/{id}")
    public ResponseEntity<PortefeuilleItem> vindPortefeuilleItemOpIdHandler(@PathVariable int id) {
        PortefeuilleItem portefeuilleItem = portefeuilleItemService.vindItemMetId(id);
        if (portefeuilleItem == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(portefeuilleItem);
        }
    }

}