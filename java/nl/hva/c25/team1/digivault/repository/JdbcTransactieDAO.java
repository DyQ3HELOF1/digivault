package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.*;
import java.util.List;

/**
 * @author Nienke
 * @author Anthon
 */

@Repository
public class JdbcTransactieDAO implements TransactieDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transactie bewaarTransacktieMetSK(Transactie transactie) {
        String sql = "INSERT INTO transactie(koperId, verkoperId, assetId, aantal, datum, tijdstip) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                buildPreparedStatement(ps, transactie);
                return ps;
            }
        } , keyHolder);
        transactie.setTransactieId(keyHolder.getKey().intValue());
        return transactie;
    }

    private void buildPreparedStatement(PreparedStatement ps, Transactie transactie) throws SQLException {
        ps.setInt(1, transactie.getKoper().getTransactiepartijId());
        ps.setInt(2,transactie.getVerkoper().getTransactiepartijId());
        ps.setInt(3,transactie.getAsset().getAssetId());
        ps.setDouble(4,transactie.getAantalCryptos());
        ps.setDate(5, Date.valueOf(transactie.getTransactieDatum()));
        ps.setTime(6,Time.valueOf(transactie.getTransactieTijd()));
    }

    @Override
    public Transactie vindTransactieOpTransactieId(int transactieId) {
        String sql = "SELECT * FROM transactie WHERE transactieId = ? ";
        Transactie transactie = null;
        try {
            transactie = jdbcTemplate.queryForObject(sql, new TransactieRowMapper(), transactieId);
        } catch (EmptyResultDataAccessException noResult) {
            System.out.println(noResult.getMessage());
        }
        return transactie;
    }

    private class TransactieRowMapper implements RowMapper<Transactie> {
        @Override
        public Transactie mapRow(ResultSet resultSet, int RowNumber) throws SQLException {
            Transactie transactie = new Transactie(
                    LocalDate.parse(resultSet.getString("datum")),
                    LocalTime.parse(resultSet.getString("tijdstip")),
                    resultSet.getDouble("aantal"));
            transactie.setTransactieId(resultSet.getInt("transactieId"));
            return transactie;
        }
    }

}
