package lion.bank.dao;

import lion.bank.dto.AccountDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO{

    @Override
    public int insertAccount(Connection conn, AccountDTO accountDTO) throws SQLException {
        String sql = "INSERT INTO account (account_number, owner_name, balance) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountDTO.getAccountNumber());
            ps.setString(2, accountDTO.getOwnerName());
            ps.setLong(3, accountDTO.getBalance());
            return ps.executeUpdate();
        }
    }

    @Override
    public AccountDTO findByAccountNumber(Connection conn, String accountNumber) throws SQLException {
        String sql = "SELECT account_id, account_number, owner_name, balance, created_at FROM account WHERE account_number = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNumber);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setAccountId(rs.getLong("account_id"));
                    accountDTO.setAccountNumber(rs.getString("account_number"));
                    accountDTO.setOwnerName(rs.getString("owner_name"));
                    accountDTO.setBalance(rs.getLong("balance"));

                    if (rs.getTimestamp("created_at") != null) {
                        accountDTO.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    }

                    return accountDTO;
                }
            }
        }

        return null;
    }

    @Override
    public List<AccountDTO> findAll(Connection conn) throws SQLException {
        String sql = "SELECT account_id, account_number, owner_name, balance, created_at FROM account ORDER BY account_id";
        List<AccountDTO> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setAccountId(rs.getLong("account_id"));
                accountDTO.setAccountNumber(rs.getString("account_number"));
                accountDTO.setOwnerName(rs.getString("owner_name"));
                accountDTO.setBalance(rs.getLong("balance"));

                if (rs.getTimestamp("created_at") != null) {
                    accountDTO.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                }

                list.add(accountDTO);
            }
        }

        return list;
    }

    @Override
    public int updateBalance(Connection conn, String accountNumber, long newBalance) throws SQLException {
        String sql = "UPDATE account SET balance = ? WHERE account_number = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, newBalance);
            ps.setString(2, accountNumber);
            return ps.executeUpdate();
        }
    }
}
