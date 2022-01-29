package nl.hva.c25.team1.digivault.controller;


import nl.hva.c25.team1.digivault.model.Rekening;
import nl.hva.c25.team1.digivault.service.RekeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// review door Erwin, 1 december

/**
 * Controller van de klasse Rekening
 * Methodes zonder javadoc comments worden niet gebruikt
 *
 * @author Sezi, studentnummer 500889525
 * @version 1-12-2021
 */

@RestController
public class RekeningController {

    private RekeningService rekeningService;

    public RekeningController(RekeningService rekeningService) {
        this.rekeningService = rekeningService;
    }

    @PostMapping("/rekeningen")
    public void bewaarRekeningMetSK(@RequestBody Rekening rekening) {
        rekeningService.bewaarRekeningMetSK(rekening);
    }

    @PutMapping("/rekeningen")
    public void verversRekening(@RequestBody Rekening rekening) {
        rekeningService.updateRekening(rekening);
    }

    @GetMapping("/rekeningen/{IBAN}")
    public Rekening vindRekeningOpIBAN(@PathVariable String IBAN) {
        return rekeningService.vindRekeningOpIBAN(IBAN);
    }

    @GetMapping("/rekeningen/{rekeningId}")
    public Rekening vindRekeningOpID(@PathVariable int rekeningId){
        return rekeningService.vindRekeningOpId(rekeningId);
    }

    @GetMapping("/rekeningen")
    public List<Rekening> geefRekeningenHandler() {
        return rekeningService.geefAlleRekeningen();
    }
}
