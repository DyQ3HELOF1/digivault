package nl.hva.c25.team1.digivault.repository;

import nl.hva.c25.team1.digivault.model.Account;
import nl.hva.c25.team1.digivault.model.Klant;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

// review door Erwin, 6 december

/**
 * Java Database Connectivity voor DB-tabel transactiepartij
 *
 * @author Sezi, studentnummer 500889525
 *
 */

@Repository
public class JdbcAccountDAO implements AccountDAO {
    JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *
     * update emailadres en wachtwoord van de account
     * @param klant
     */

    @Override
    public void updateAccount(Klant klant) {
        String sql = "UPDATE transactiepartij SET emailadres = ?, wachtwoord = ? WHERE tpId = ?;";
        jdbcTemplate.update(sql, klant.getAccount().getEmailadres(), klant.getAccount().getWachtwoord(),
        klant.getTransactiepartijId());
    }

    /**
     *
     * vind account op basis van klantId
     * @param klantId
     */

    @Override
    public Account vindAccountOpKlantId(int klantId) {
        String sql = "SELECT emailadres, wachtwoord FROM transactiepartij WHERE tpId = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), klantId);
    }

    /**
     *
     * vind account op basis van emailAdres
     * @param emailAdres
     */

    @Override
    public Account vindAccountOpEmailAdres(String emailAdres) {
        String sql = "SELECT emailadres, wachtwoord FROM transactiepartij WHERE emailadres = ?";
        Account account;
        try {
            account = jdbcTemplate.queryForObject(sql, new AccountRowMapper(), emailAdres);
        } catch (EmptyResultDataAccessException noResult) {
            account = null;
        }
        return account;
    }

    /**
     *
     * @return List<Account> geeft lijst van alle accounts uit DB terug
     */

    @Override
    public List<Account> geefAlleAccounts() {
        String sql = "SELECT emailadres, wachtwoord FROM transactiepartij";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    private class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Account(resultSet.getString("emailadres"), resultSet.getString("wachtwoord"));
        }
    }
}
