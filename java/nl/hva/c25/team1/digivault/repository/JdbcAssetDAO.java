package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Asset;
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

// review door Sezi, 6 december

/**
 * Java Database Connectivity voor DB-tabel Asset
 *
 * @author Erwin, studentnummer 500889293
 * @since 1-12-2021
 */

@Repository
public class JdbcAssetDAO implements AssetDAO {

    JdbcTemplate jdbcTemplate;

    /**
     * Constructor van JdbcAssetDAO
     * @param jdbcTemplate Jdbc Template
     */
    @Autowired
    public JdbcAssetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Slaat Asset met surrogate key (SK) op in Database en geeft Asset terug
     * @param asset de te bewaren Asset
     * @return de bewaarde Asset
     */
    @Override
    public Asset bewaarAssetMetSK(Asset asset) {
        String sql = "INSERT INTO asset (afkorting, naam) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, asset.getAfkorting());
                preparedStatement.setString(2, asset.getNaam());
                return preparedStatement;
            }
        }, keyHolder);
        asset.setAssetId(keyHolder.getKey().intValue());
        return asset;
    }

    /**
     * Vindt Asset op assetId in Database
     * @param assetId waarop Asset gezocht wordt
     * @return de bijbehorende Asset
     */
    @Override
    public Asset vindAssetOpId(int assetId) {
        String sql = "SELECT * FROM asset WHERE assetId = ?";
        Asset asset;
        try {
            asset = jdbcTemplate.queryForObject(sql, new AssetRowMapper(), assetId);
        } catch (EmptyResultDataAccessException noResult) {
            asset = null;
        }
        return asset;
    }

    /**
     * Geeft een lijst van alle Assets terug
     * @return lijst met alle Assets
     */
    @Override
    public List<Asset> geefAlleAssets() {
        String sql = "SELECT * FROM asset";
        return jdbcTemplate.query(sql, new AssetRowMapper());
    }

    /**
     * Updatet een bepaalde Asset
     * @param asset de te updaten Asset
     */
    @Override
    public void update(Asset asset) {
        String sql = "UPDATE asset SET assetId = ?, afkorting = ?, naam = ? ";
        jdbcTemplate.update(sql, asset.getAfkorting(), asset.getNaam());
    }

    private class AssetRowMapper implements RowMapper<Asset> {
        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Asset(resultSet.getInt("assetId"), resultSet.getString("afkorting"),
                    resultSet.getString("naam"));
        }
    }
}
