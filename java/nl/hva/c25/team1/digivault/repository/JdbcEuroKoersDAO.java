package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.model.EuroKoers;
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
 * Java Database Connectivity voor DB-tabel EuroKoers
 *
 * @author Erwin, studentnummer 500889293
 * @since 2-12-2021
 */

@Repository
public class JdbcEuroKoersDAO implements EuroKoersDAO {

    JdbcTemplate jdbcTemplate;

    /**
     * Constructor van JdbcEuroKoersDAO
     * @param jdbcTemplate JDBC Template
     */
    @Autowired
    public JdbcEuroKoersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Slaat EuroKoers met surrogate key (SK) op in Database en geeft EuroKoers terug
     * @param euroKoers de te bewaren EuroKoers
     * @return de bewaarde EuroKoers
     */
    @Override
    public EuroKoers bewaarEuroKoersMetSK(EuroKoers euroKoers) {
        String sql = "INSERT INTO eurokoers (datum, koers, assetId) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDate(1, Date.valueOf(euroKoers.getDatum()));
                preparedStatement.setDouble(2, euroKoers.getKoers());
                preparedStatement.setInt(3, euroKoers.getAssetId());
                return preparedStatement;
            }
        }, keyHolder);
        euroKoers.setEuroKoersId(keyHolder.getKey().intValue());
        return euroKoers;
    }

    /**
     * Vindt EuroKoers op euroKoersId in Database
     * @param euroKoersId waarop EuroKoers gezocht wordt
     * @return de bijbehorende Asset
     */
    @Override
    public EuroKoers vindEuroKoersOpId(int euroKoersId) {
        String sql = "SELECT * FROM eurokoers WHERE euroKoersId = ?";
        EuroKoers euroKoers;
        try {
            euroKoers = jdbcTemplate.queryForObject(sql, new EuroKoersRowMapper(), euroKoersId);
        } catch (EmptyResultDataAccessException noResult) {
            euroKoers = null;
        }
        return euroKoers;
    }

    /**
     * Geeft een lijst van alle EuroKoersen terug
     * @return Lijst met alle EuroKoersen
     */
    @Override
    public List<EuroKoers> geefAlleEuroKoersen() {
        String sql = "SELECT * FROM eurokoers";
        return jdbcTemplate.query(sql, new EuroKoersRowMapper());
    }

    /**
     * Updatet een bepaalde EuroKoers
     * @param euroKoers de te updaten EuroKoers
     */
    @Override
    public void update(EuroKoers euroKoers) {
        String sql = "UPDATE eurokoers SET eurokoersId = ?, datum = ?, koers = ?, assetId = ?";
        jdbcTemplate.update(sql, euroKoers.getEuroKoersId(), euroKoers.getDatum(), euroKoers.getKoers(),
                euroKoers.getAssetId());
    }

    /**
     * Methode om de meeste recente koers van een Asset op te halen
     * @param asset waar koers bij gezocht wordt
     * @return bijbehorende Eurokoers
     */
    @Override
    public EuroKoers vindMeestRecenteKoersAsset(Asset asset) {
        String sql = "SELECT * FROM eurokoers WHERE assetId=? AND datum=?";
        return jdbcTemplate.queryForObject(sql, new EuroKoersRowMapper(), asset.getAssetId(),
                vindDatumMeestRecenteKoersAsset(asset).toString());
    }

    /**
     * Methode om de datum bij de meest recente Eurokoers op te zoeken
     * @param asset waar koers bij gezocht wordt
     * @return bijbehorende LocalDate
     */
    public LocalDate vindDatumMeestRecenteKoersAsset(Asset asset) {
        String sql = "SELECT max(datum) FROM eurokoers WHERE assetId=?";
        String datumString = jdbcTemplate.queryForObject(sql, String.class, asset.getAssetId());
        return LocalDate.parse(datumString);
    }

    private class EuroKoersRowMapper implements RowMapper<EuroKoers> {
        @Override
        public EuroKoers mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new EuroKoers(resultSet.getInt("euroKoersId"),
                    resultSet.getDate("datum").toLocalDate(), resultSet.getDouble("koers"));
        }
    }
}
