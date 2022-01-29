package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.service.EuroKoersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller van de klasse Asset
 *
 * @author Erwin, studentnummer 500889293
 * @since 2-12-2021
 */

@RestController
public class EuroKoersController {

    private final EuroKoersService euroKoersService;

    /**
     * Constructor van de EuroKoersController
     * @param euroKoersService EuroKoersService
     */
    @Autowired
    public EuroKoersController(EuroKoersService euroKoersService) {
        super();
        this.euroKoersService = euroKoersService;
    }

    /**
     * Slaat een EuroKoers op
     * @param euroKoers de te bewaren EuroKoers
     */
    @PostMapping("/eurokoersen")
    public void bewaarEuroKoersMetSK(@RequestBody EuroKoers euroKoers) {
        euroKoersService.bewaarEuroKoersMetSK(euroKoers);
    }

    /**
     * Vindt EuroKoers op assetId
     * @param euroKoersId waarop EuroKoers gezocht wordt
     * @return doorgeefluik
     */
    @GetMapping("/eurokoersen/{euroKoersId}")
    public EuroKoers vindEuroKoersOpId(@PathVariable int euroKoersId) {
        return euroKoersService.vindEuroKoersOpId(euroKoersId);
    }

    /**
     * Geeft een lijst van alle EuroKoersen terug
     * @return doorgeefluik
     */
    @GetMapping("/eurokoersen")
    public List<EuroKoers> geefAlleEuroKoersen() {
        return euroKoersService.geefAlleEuroKoersen();
    }

    /**
     * Updatet een bepaalde EuroKoers
     * @param euroKoers de te updaten EuroKoers
     * @return doorgeefluik
     */
    @PutMapping("/eurokoersen/{euroKoersId}")
    public String update(@RequestBody EuroKoers euroKoers) {
        return euroKoersService.updateEuroKoers(euroKoers);
    }
}
