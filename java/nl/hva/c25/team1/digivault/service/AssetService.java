package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.repository.JdbcAssetDAO;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// review door Sezi, 6 december

/**
 * Service van de klasse Asset
 *
 * @author Erwin, studentnummer 500889293
 * @since 1-12-2021
 */

@Service
public class AssetService {

    private final JdbcAssetDAO jdbcAssetDAO;
    private final RootRepository rootRepository;

    @Autowired
    public AssetService(JdbcAssetDAO jdbcAssetDAO, RootRepository rootRepository) {
        super();
        this.jdbcAssetDAO = jdbcAssetDAO;
        this.rootRepository = rootRepository;
    }

    /**
     * Slaat een nieuwe Asset op
     * @param asset de te bewaren Asset
     */
    public void bewaarAssetMetSK(Asset asset) {
        jdbcAssetDAO.bewaarAssetMetSK(asset);
    }

    /**
     * Vindt Asset bij opgegeven assetId
     * @param assetId waarop Asset gezocht wordt
     * @return de bijbehorende Asset
     */
    public Asset vindAssetOpId(int assetId) {
        return rootRepository.vindAssetOpId(assetId);
    }

    /**
     * Geeft een lijst van alle Assets terug
     * @return lijst met alle Assets
     */
    public List<Asset> geefAlleAssets() {
        return jdbcAssetDAO.geefAlleAssets();
    }

    /**
     * Updatet een bepaalde Asset
     * @param asset de te updaten Asset
     * @return String-melding of updaten gelukt is
     */
    public String updateAsset(Asset asset) {
        if (jdbcAssetDAO.vindAssetOpId(asset.getAssetId()) == null) {
            return "Asset is niet gevonden, het updaten is mislukt.";
        } else {
            jdbcAssetDAO.update(asset);
            return "Asset is geüpdatet.";
        }
    }
}
