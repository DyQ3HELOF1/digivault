package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel Klant
 *
 * @author Anneke, studentnummer 500889251
 * @since 3-12-2021
 *
 * Review Anthon 7-12-2021
 */

@Repository
public class JdbcKlantDAO implements KlantDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcKlantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * slaat klant op in database en genereert een surrogate key
     * @param klant die opgeslagen moet worden
     * @return int klantID, de automatisch gegenereerde surrogate key
     */
    @Override
    public Klant bewaarKlantMetSK(Klant klant) {
        String sql = "INSERT INTO transactiepartij (rekeningId,adresId,tpType, emailadres, wachtwoord," +
                "voornaam, tussenvoegsel, achternaam, bsn, geboortedatum) VALUES (?,?,?,?,?,?,?,?,?,?);";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                bouwPs(ps, klant);
                return ps;
            }
        } , keyholder);
        klant.setTransactiepartijId(keyholder.getKey().intValue());
        return klant;
    }

    private PreparedStatement bouwPs(PreparedStatement ps, Klant klant) throws SQLException {
        ps.setInt(1, klant.getRekening().getRekeningId());
        ps.setInt(2, klant.getAdres().getAdresId());
        ps.setString(3, "klant");
        ps.setString(4, klant.getAccount().getEmailadres());
        ps.setString(5, klant.getAccount().getWachtwoord());
        ps.setString(6, klant.getNaam().getVoornaam());
        ps.setString(7, klant.getNaam().getTussenvoegsel());
        ps.setString(8, klant.getNaam().getAchternaam());
        ps.setString(9, klant.getBsn());
        ps.setDate(10, java.sql.Date.valueOf(klant.getGeboortedatum()));
        return ps;
    }

    /**
     *
     * vindt een klant in database adhv klantID
     * @param klantId van klant die je wilt vinden
     * @return Klant
     */
    public Klant vindKlantOpKlantId(int klantId) {
        String sql = "SELECT * FROM transactiepartij  WHERE tpId = ? ;";
        Klant klant;
        try {
            klant = jdbcTemplate.queryForObject(sql, new KlantRowMapper(), klantId);
        } catch (EmptyResultDataAccessException noResult) {
            klant = null;
        }
        return klant;
    }

    /**
     *
     * vindt een klant in database adhv emailadres
     * @param email van klant
     * @return Klant
     */
    @Override
    public Klant vindKlantOpEmailadres(String email) {
        String sql = "SELECT * FROM transactiepartij WHERE emailadres = ?;";
        return jdbcTemplate.queryForObject(sql, new KlantRowMapper(), email);
    }
    
    /**
     *
     * @return List<Klant> geeft lijst van alle klanten uit DB terug
     */
    @Override
    public List<Klant> vindAlleKlanten() {
        String sql = "SELECT * FROM transactiepartij WHERE tpId > 9;";
        System.out.println("jdbc");
        return jdbcTemplate.query(sql, new KlantRowMapper());
    }

    /**
     *
     * update gegevens van klant
     * @param klant van klant die je wilt updaten
     */
    @Override
    public void update(Klant klant) {
        String sql = "UPDATE transactiepartij SET bsn = ?, geboortedatum = ? WHERE tpId= ?;";
        jdbcTemplate.update(sql, klant.getBsn(),klant.getGeboortedatum(), klant.getTransactiepartijId());
    }


    private class KlantRowMapper implements RowMapper<Klant> {
        @Override
        public Klant mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Klant(resultSet.getInt("tpId"), resultSet.getString("bsn"),
                    LocalDate.parse(resultSet.getString("geboortedatum")));
        }
    }

}
