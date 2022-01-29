package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Rekening;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

// review door Erwin, 1 december

/**
 * Java Database Connectivity voor DB-tabel Rekening
 *
 * @author Sezi, studentnummer 500889525
 *
 */

@Repository
public class JdbcRekeningDAO implements RekeningDAO{

    JdbcTemplate jdbcTemplate;

    public JdbcRekeningDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bewaar(Rekening rekening) {
        String sql = "Insert into rekening values (?,?)";
        jdbcTemplate.update(sql, rekening.getIBAN(), rekening.getSaldo());
    }

    /**
     *
     * slaat rekening op in database en genereert een surrogate key
     * @param rekening die opgeslagen moet worden
     * @return int rekeningId, de automatisch gegenereerde surrogate key
     */

    @Override
    public void bewaarRekeningMetSK(Rekening rekening) {
        String sql = "Insert into rekening (IBAN, saldo) values (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, rekening.getIBAN());
                ps.setDouble(2, rekening.getSaldo());
                return ps;
            }
        }, keyHolder);
        rekening.setRekeningId(keyHolder.getKey().intValue());
    }

    /**
     *
     * ververs gegevens van klant
     * @param rekening
     */

    @Override
    public void updateRekening(Rekening rekening) {
        String sql = "Update rekening Set saldo = ? WHERE rekeningId = ? ;";
        jdbcTemplate.update(sql, rekening.getSaldo(), rekening.getRekeningId());
    }

    /**
     *
     * vindt een klant in database adhv IBAN
     * @param IBAN
     * @return Rekening
     */

    @Override
    public Rekening vindRekeningOpIBAN(String IBAN) {
        String sql = "Select * From rekening Where IBAN = ?";
        return jdbcTemplate.queryForObject(sql, new JdbcRekeningDAO.RekeningRowMapper(), IBAN);
    }


    @Override
    public Rekening vindRekeningOpId(int rekeningId) {
        String sql = "Select * From rekening Where rekeningId = ?";
        return jdbcTemplate.queryForObject(sql, new JdbcRekeningDAO.RekeningRowMapper(), rekeningId);
    }

    /**
     *
     * @author Anneke
     * @param transactiepartijId
     * @return Rekening gevonden op transactiepartijID
     */
    @Override
    public Rekening vindRekeningOpTransactiePartijId(int transactiepartijId) {
        String sql = "SELECT r.rekeningId, iban, saldo FROM rekening r JOIN transactiepartij t ON r.rekeningId = t.rekeningId WHERE tpId = ?";
        return jdbcTemplate.queryForObject(sql, new JdbcRekeningDAO.RekeningRowMapper(), transactiepartijId);
    }

    /**
     *
     * @return List<Rekening> geeft lijst van alle rekeningen uit DB terug
     */

    @Override
    public List<Rekening> geefAlleRekeningen() {
        String sql = "Select * From rekening";
        return jdbcTemplate.query(sql, new JdbcRekeningDAO.RekeningRowMapper());
    }

    private class RekeningRowMapper implements RowMapper<Rekening> {
        @Override
        public Rekening mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Rekening(resultSet.getInt("RekeningId"),
                    resultSet.getString("IBAN"), resultSet.getDouble("Saldo"));
        }
    }
}
