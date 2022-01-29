package nl.hva.c25.team1.digivault.controller;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// review door Sezi, 6 december

/**
 * Controller van de klasse Asset
 *
 * @author Erwin, studentnummer 500889293
 * @since 1-12-2021
 */

@RestController
public class AssetController {

    private final AssetService assetService;

    /**
     * Constructor van de AssetController
     * @param assetService AssetService
     */
    @Autowired
    public AssetController(AssetService assetService) {
        super();
        this.assetService = assetService;
    }

    /**
     * Slaat een Asset op
     * @param asset de te bewaren Asset
     */
    @PostMapping("/assets")
    public Asset bewaarAssetMetSK(@RequestBody Asset asset) {
        assetService.bewaarAssetMetSK(asset);
        return asset;
    }

    /**
     * Vindt Asset op assetId
     * @param assetId waarop Asset gezocht wordt
     * @return doorgeefluik
     */
    @GetMapping("/assets/{assetId}")
    public Asset vindAssetOpId(@PathVariable int assetId) {
        return assetService.vindAssetOpId(assetId);
    }

    /**
     * Geeft een lijst van alle Assets terug
     * @return doorgeefluik
     */
    @GetMapping("/assets")
    public List<Asset> geefAlleAssets() {
        return assetService.geefAlleAssets();
    }

    /**
     * Updatet een bepaalde Asset
     * @param asset de te updaten Asset
     * @return doorgeefluik
     */
    @PutMapping("/assets/{assetId}")
    public String update(@RequestBody Asset asset) {
        return assetService.updateAsset(asset);
    }
}
