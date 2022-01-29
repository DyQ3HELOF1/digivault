package nl.hva.c25.team1.digivault.dagkoersservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.repository.AssetDAO;
import nl.hva.c25.team1.digivault.repository.EuroKoersDAO;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

/**
 *
 * @author Anneke
 *
 */
@Configuration
@EnableScheduling
@Service
public class DagkoersService {
    private final EuroKoersDAO euroKoersDAO;
    private final AssetDAO assetDAO;
    public final double DOLLARKOERS = 0.88;
    public final String URL = "https://api.coinranking.com/v2/coins";
    public final String PATH = "/data/coins";
    public final int MIN_ASSET = 1;
    public final int MAX_ASSET = 20;
    public final String SYMBOL = "symbol";
    public final String PRICE = "price";
    public final String ZONE = "CET";
    public final String OPHAALTIJD = "0 00 01 * * ?";

    public DagkoersService(EuroKoersDAO euroKoersDAO, AssetDAO assetDAO) {
        super();
        this.euroKoersDAO = euroKoersDAO;
        this.assetDAO = assetDAO;
    }

    /**
     *
     * Deze methode wordt elke dag om 1:00 uur automatisch aangeroepen.
     * Via een RestTemplate wordt met een GET request een response gehaald van coinranking.com
     * In de response staan o.a. de huidige dagkoers van verschillende cryptomunten
     * Om JSON te lezen en om te zetten naar andere datatypes wordt Jackson gebruikt.
     * @throws JsonProcessingException jsonProcessingException
     */
    @Scheduled(cron = OPHAALTIJD, zone = ZONE) // elke dag om 1 am
    public void dagkoersBuilder() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response
                = restTemplate.getForEntity(URL, String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode coins = root.at(PATH);
        bouwEuroKoersEnSlaOp(coins);
    }

    /**
     *
     * Deze methode gaat al onze assets af, vanaf assetId 1 en vergelijkt de afkorting van de asset
     * met de afkortingen in de responseJson. Als de afkorting hetzelfde is wordt de
     * slaEuroKoersOp-methode aangeroepen
     * @param coins array met coins en bijbehorende dagkoers (response)
     */
    public void bouwEuroKoersEnSlaOp(JsonNode coins) {
        EuroKoers euroKoers = new EuroKoers();
        for (int i = MIN_ASSET; i <= MAX_ASSET; i++) {
            Asset asset = assetDAO.vindAssetOpId(i);
            for (JsonNode jsonNode : coins) {
                String afkorting = jsonNode.get(SYMBOL).asText();
                if ((asset.getAfkorting()).equals(afkorting)) {
                    slaEuroKoersOp(i, euroKoers,jsonNode);
                }
            }
        }
    }

    /**
     *
     * de dagkoers van van asset is bekend. De gehele eurokoers kan nu opgeslagen worden
     * dmv setters en de eurokoersDAO bewaarmethode
     * @param i assetId
     * @param euroKoers eurokoers die opgeslagen gaat worden
     * @param jsonNode jsonNode met zelfde afkorting (symbol) als asset die bij eurokoers hoort
     */
    public void slaEuroKoersOp(int i, EuroKoers euroKoers, JsonNode jsonNode) {
        JsonNode dagkoers = jsonNode.get(PRICE);
        euroKoers.setAssetId(i);
        euroKoers.setEuroKoersId(i);
        euroKoers.setDatum(LocalDate.now());
        euroKoers.setKoers(dagkoers.asDouble() * DOLLARKOERS);
        euroKoersDAO.bewaarEuroKoersMetSK(euroKoers);
    }

}
