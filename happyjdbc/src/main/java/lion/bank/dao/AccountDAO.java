package lion.bank.dao;

import lion.bank.dto.AccountDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    int insertAccount(Connection conn, AccountDTO accountDTO) throws SQLException;

    AccountDTO findByAccountNumber(Connection conn, String accountNumber) throws SQLException;

    List<AccountDTO> findAll(Connection conn) throws SQLException;

    int updateBalance(Connection conn, String accountNumber, long newBalance) throws SQLException;
}
