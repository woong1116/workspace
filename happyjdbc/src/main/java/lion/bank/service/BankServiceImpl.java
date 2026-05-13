package lion.bank.service;

import lion.bank.common.DBUtil;
import lion.bank.dao.AccountDAO;
import lion.bank.dao.AccountDAOImpl;
import lion.bank.dto.AccountDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BankServiceImpl implements BankService{

    private final AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public boolean createAccount(String accountNumber, String ownerName, long initialBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            return false;
        }

        if (ownerName == null || ownerName.isBlank()) {
            return false;
        }

        if (initialBalance < 0) {
            return false;
        }

        try (Connection conn = DBUtil.getConnection()) {
            AccountDTO exists = accountDAO.findByAccountNumber(conn, accountNumber);
            if (exists != null) {
                return false;
            }

            AccountDTO accountDTO = new AccountDTO(accountNumber, ownerName, initialBalance);
            return accountDAO.insertAccount(conn, accountDTO) > 0;
        } catch (SQLException e) {
            System.out.println("계좌 생성 중 오류가 발생했습니다. " + e.getMessage());
            return false;
        }
    }

    @Override
    public AccountDTO getAccount(String accountNumber) {
        try (Connection conn = DBUtil.getConnection()) {
            return accountDAO.findByAccountNumber(conn, accountNumber);
        } catch (SQLException e) {
            System.out.println("계좌 조회 중 오류가 발생했습니다. " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        try (Connection conn = DBUtil.getConnection()) {
            return accountDAO.findAll(conn);
        } catch (SQLException e) {
            System.out.println("계좌 목록 조회 중 오류가 발생했습니다. " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deposit(String accountNumber, long amount) {
        if (amount <= 0) {
            return false;
        }

        try (Connection conn = DBUtil.getConnection()) {
            AccountDTO accountDTO = accountDAO.findByAccountNumber(conn, accountNumber);
            if (accountDTO == null) {
                return false;
            }

            long newBalance = accountDTO.getBalance() + amount;
            return accountDAO.updateBalance(conn, accountNumber, newBalance) > 0;
        } catch (SQLException e) {
            System.out.println("입금 중 오류가 발생했습니다. " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean withdraw(String accountNumber, long amount) {
        if (amount <= 0) {
            return false;
        }

        try (Connection conn = DBUtil.getConnection()) {
            AccountDTO accountDTO = accountDAO.findByAccountNumber(conn, accountNumber);
            if (accountDTO == null) {
                return false;
            }

            if (accountDTO.getBalance() < amount) {
                return false;
            }

            long newBalance = accountDTO.getBalance() - amount;
            return accountDAO.updateBalance(conn, accountNumber, newBalance) > 0;
        } catch (SQLException e) {
            System.out.println("출금 중 오류가 발생했습니다. " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean transfer(String fromAccountNumber, String toAccountNumber, long amount) {
        if (amount <= 0) {
            return false;
        }

        if (fromAccountNumber == null || toAccountNumber == null) {
            return false;
        }

        if (fromAccountNumber.equals(toAccountNumber)) {
            return false;
        }

        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);

            AccountDTO fromAccount = accountDAO.findByAccountNumber(conn, fromAccountNumber);
            AccountDTO toAccount = accountDAO.findByAccountNumber(conn, toAccountNumber);

            if (fromAccount == null || toAccount == null) {
                conn.rollback();
                return false;
            }

            if (fromAccount.getBalance() < amount) {
                conn.rollback();
                return false;
            }

            long fromNewBalance = fromAccount.getBalance() - amount;
            long toNewBalance = toAccount.getBalance() + amount;

            int result1 = accountDAO.updateBalance(conn, fromAccountNumber, fromNewBalance);
            int result2 = accountDAO.updateBalance(conn, toAccountNumber, toNewBalance);

            if (result1 > 0 && result2 > 0) {
                conn.commit();
                return true;
            }

            conn.rollback();
            return false;
        } catch (SQLException e) {
            System.out.println("이체 중 오류가 발생했습니다. " + e.getMessage());
            return false;
        }
    }
}
