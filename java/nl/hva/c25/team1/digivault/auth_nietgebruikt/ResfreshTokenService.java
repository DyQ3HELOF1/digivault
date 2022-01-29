package nl.hva.c25.team1.digivault.auth_nietgebruikt;

//public class ResfreshTokenService {

    //    code geschreven voor gebruik resfresh token UUID
//
//    public void genereerNieuweTokens(UUID token, Klant klant){
//        // JWT is verlopen, klant krijgt 401
//        // vanuit de controller wordt om refresh token van klant gevraagd
//        // valideer deze refresh token, als hij niet leeg terug komt dan krijgt klant 2 nieuwe tokens
//        if (!(valideer(token).isEmpty())){
//            // maak nieuwe jwt:
//            String jwt = maakJWT(klant.getAccount().getEmailadres());
//            // maak nieuwe refresh
//            TokenKlantPaar tokenKlantPaar = authoriseer(klant);
//        }
//        /* TD: geeft deze methode iets terug? TokenKlantPaar? JWT komt in header getPortefeuille */
//    }

//    public TokenKlantPaar authoriseer(Klant klant) {
//        Optional<TokenKlantPaar> paarOptie = tokenKlantPaarDAO.vindPaarOpKlant(klant);
//        if (paarOptie.isPresent()) {
//            tokenKlantPaarDAO.delete(paarOptie.get().getKey());
//        }
//        UUID token = UUID.randomUUID();
//        TokenKlantPaar tokenKlantPaar = new TokenKlantPaar(token, klant);
//        tokenKlantPaarDAO.save(tokenKlantPaar);
//        return tokenKlantPaar;
//    }
//
//    public Optional<Klant> valideer(UUID token) {
//        Optional<TokenKlantPaar> paarOptie = tokenKlantPaarDAO.vindOpKey(token);
//        if (paarOptie.isPresent()) {
//            return Optional.of(paarOptie.get().getKlant());
//        }
//        return Optional.empty();
//    }
//}
