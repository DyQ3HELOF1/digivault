package nl.hva.c25.team1.digivault.dagkoersservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Anneke
 * Deze controller wordt niet aangeroepen in Javascript, maar is gemaakt
 * om handmatig te checken of de methodes in de DagkoersService werken.
 * Dus of de huidige dagkoers dmv API call naar Coinranking.com wordt opgehaald
 */
@RestController
public class DagkoersController {

    private DagkoersService dagkoersService;

    @Autowired
    public DagkoersController(DagkoersService dagkoersService) {
        super();
        this.dagkoersService = dagkoersService;
    }

    @CrossOrigin
    @GetMapping("/dagkoersen")
    public String geefDagkoersen() throws JsonProcessingException {
        dagkoersService.dagkoersBuilder();
        return "Geslaagd!";
    }

}
