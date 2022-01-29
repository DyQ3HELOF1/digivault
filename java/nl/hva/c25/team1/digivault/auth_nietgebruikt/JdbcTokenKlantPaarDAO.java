//package nl.hva.c25.team1.digivault.authentication;
//
//import nl.hva.c25.team1.digivault.model.Klant;
//import nl.hva.c25.team1.digivault.repository.KlantDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
///**
// *
// * @author Anneke, studentnummer 500889251
// * @since 15-12-2021
// *
// */
//
//@Repository
//public class JdbcTokenKlantPaarDAO implements TokenKlantPaarDAO{
//
//    private JdbcTemplate jdbcTemplate;
//    private KlantDAO klantDAO;
//
//    @Autowired
//    public JdbcTokenKlantPaarDAO(JdbcTemplate jdbcTemplate, KlantDAO klantDAO) {
//        super();
//        this.jdbcTemplate = jdbcTemplate;
//        this.klantDAO = klantDAO;
//    }
//
//    @Override
//    public Optional<TokenKlantPaar> vindPaarOpKlant(Klant klant) {
//        String sql = "SELECT * FROM tokenKlantPaar WHERE klantId = ?;";
//        List<TokenKlantPaar> tokenKlantParen =
//                jdbcTemplate.query(sql, new TokenKlantRowMapper(), klant.getKlantId());
//        if (tokenKlantParen.size() == 1) {
//            return Optional.of(tokenKlantParen.get(0));
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(TokenKlantPaar tokenKlantPaar) {
//        String sql = "INSERT INTO tokenKlantPaar (uuid, klantId) VALUES (?, ?);";
//        jdbcTemplate.update(sql, tokenKlantPaar.getKey().toString(),
//                tokenKlantPaar.getKlant().getKlantId());
//    }
//
//    @Override
//    public Optional<TokenKlantPaar> vindOpKey(UUID key) {
//        String sql = "SELECT * FROM tokenKlantPaar WHERE uuid = ?;";
//        List<TokenKlantPaar> tokenKlantParen =
//                jdbcTemplate.query(sql, new TokenKlantRowMapper(), key.toString());
//        if (tokenKlantParen.size() == 1) {
//            return Optional.of(tokenKlantParen.get(0));
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public void delete(UUID uuid) {
//        String sql = "DELETE FROM tokenKlantPaar WHERE uuid = ?;";
//        jdbcTemplate.update(sql, uuid.toString());
//    }
//
//    private class TokenKlantRowMapper implements RowMapper<TokenKlantPaar> {
//
//        @Override
//        public TokenKlantPaar mapRow(ResultSet resultSet, int i) throws SQLException {
//            UUID uuid = UUID.fromString(resultSet.getString("uuid"));
//            int klantId = resultSet.getInt("klantId");
//            Klant klant = klantDAO.vindKlantOpKlantId(klantId);
//            return new TokenKlantPaar(uuid, klant);
//        }
//    }
//}
