package lion.bank.service;

import lion.bank.dto.AccountDTO;

import java.util.List;

public interface BankService {

    boolean createAccount(String accountNumber, String ownerName, long initialBalance);

    AccountDTO getAccount(String accountNumber);

    List<AccountDTO> getAllAccounts();

    boolean deposit(String accountNumber, long amount);

    boolean withdraw(String accountNumber, long amount);

    boolean transfer(String fromAccountNumber, String toAccountNumber, long amount);
}
