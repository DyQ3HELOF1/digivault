package nl.hva.c25.team1.digivault.model;

// review door Sezi, 1 december

/**
 * Enum met de namen van de top 20 populairste assets, peildatum 1 december 2021
 *
 * @author Erwin, studentnummer 500889293
 * @since 1-12-2021
 */

public enum AssetNaam {
    BITCOIN("Bitcoin"),
    ETHEREUM("Ethereum"),
    BINANCE_COIN("Binance Coin"),
    SOLANA("Solana"),
    CARDANO("Cardano"),
    XRP("XRP"),
    POLKADOT("Polkadot"),
    DOGECOIN("Dogecoin"),
    AVALANCHE("Avalanche"),
    TERRA("Terra"),
    LITECOIN("Litecoin"),
    POLYGON("Polygon"),
    ALGORAND("Algorand"),
    BITCOIN_CASH("Bitcoin Cash"),
    STELLAR("Stellar"),
    VECHAIN("VeChain"),
    INTERNET_COMPUTER("Internet Computer"),
    HEX("HEX"),
    ELROND("Elrond"),
    SHIBA_INU("Shiba Inu");

    private final String naamAlsString;

    /**
     *
     * @param naamAlsString naamAlsString
     */
    AssetNaam(String naamAlsString) {
        this.naamAlsString = naamAlsString;
    }

    /**
     * ToString-methode AssetNaam
     * @return toString van enum AssetNaam
     */
    @Override
    public String toString() {
        return naamAlsString;
    }
}
