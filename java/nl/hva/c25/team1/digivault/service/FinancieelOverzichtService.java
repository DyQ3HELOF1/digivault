package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.FinancieelOverzicht;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service can de klasse FinancieelOverzicht
 *
 * @author Erwin en Nienke
 * @since 9-12-2021
 */

@Service
public class FinancieelOverzichtService {

    private final RootRepository rootRepository;

    /**
     * Constructor van de service FinancieelOverzichtService
     * @param rootRepository RootRepository
     */
    @Autowired
        public FinancieelOverzichtService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }

    /**
     * Service-methode voor genereren financieel overzicht op klantId
     * @param klantId waarvoor een financieel overzicht wordt gegenereerd
     * @return geeft FinancieelOverzicht terug
     */
    public FinancieelOverzicht genereerFinancieelOverzicht(int klantId) {
        return rootRepository.genereerFinancieelOverzichtOpId(klantId);
    }
}
