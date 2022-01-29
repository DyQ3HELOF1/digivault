package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;

import java.util.List;

/**
 * @author Anthon van Dijk (studentnummer 500889247)
 * 09-12-2021 review Anneke
 */
public interface PortefeuilleItemDAO {

    PortefeuilleItem bewaarPortefeuilleItemMetKey(PortefeuilleItem portefeuilleItem);

    void updatePortefeuilleItem(PortefeuilleItem portefeuilleItem);

    List<PortefeuilleItem> genereerPortefeuilleVanTransactiepartijMetId(int tpId);

    PortefeuilleItem vindItemMetId(int itemId);

    int vindKlantIdVanPortefeuilleitem(PortefeuilleItem portefeuilleItem);

    int vindAssetIdVanPortefeuilleItem(PortefeuilleItem portefeuilleItem);
}
