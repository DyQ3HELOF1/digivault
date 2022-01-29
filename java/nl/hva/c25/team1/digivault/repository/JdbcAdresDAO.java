package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Adres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * Java Database Connectivity voor DB-tabel Adres
 *
 * @author Anneke, studentnummer 500889251
 * @since 3-12-2021
 */

@Repository
public class JdbcAdresDAO implements AdresDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdresDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * slaat adres van klant op in database en genereert een surrogate key
     * @param adres welke opgeslagen moet worden
     * @return int adresID, de automatisch gegenereerde surrogate key
     */
    @Override
    public Adres bewaarAdresMetSK(Adres adres) {
        String sql = "INSERT INTO adres (postcode, huisnummer, toevoeging) VALUES (?, ?, ?);";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, adres.getPostcode());
                ps.setInt(2, adres.getHuisnummer());
                ps.setString(3, adres.getToevoeging());
                return ps;
            }
        } , keyholder);
        adres.setAdresId(keyholder.getKey().intValue());
        return adres;
    }


    /**
     * vindt een adres in database adhv adresID
     * @param adresId van adres dat je wilt vinden
     * @return Adres
     */
    @Override
    public Adres vindAdresOpAdresId(int adresId) {
        String sql = "SELECT * FROM adres WHERE adresId = ?";
        Adres adres;
        try {
            adres = jdbcTemplate.queryForObject(sql, new AdresRowMapper(), adresId);
        } catch (EmptyResultDataAccessException noResult) {
            adres = null;
        }
        return adres;
    }

    /**
     * geeft een lijst met alle adressen uit de database terug
     * @return List<Adres>
     */
    @Override
    public List<Adres> vindAlleAdressen() {
        String sql = "SELECT * FROM adres";
        return jdbcTemplate.query(sql, new JdbcAdresDAO.AdresRowMapper());
    }

    /**
     * update adres van klant
     * @param adres van adres dat je wilt updaten
     */
    @Override
    public void update(Adres adres) {
        String sql = "UPDATE adres SET postcode = ?, huisnummer = ?, toevoeging = ? WHERE adresId = ?";
        jdbcTemplate.update(sql, adres.getPostcode(), adres.getHuisnummer(), adres.getToevoeging(), adres.getAdresId());
    }

    private class AdresRowMapper implements RowMapper<Adres> {
        @Override
        public Adres mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            return new Adres(resultSet.getInt("adresId"),  resultSet.getString("postcode"),
                    resultSet.getInt("huisnummer"), resultSet.getString("toevoeging"));
        }
    }
}
