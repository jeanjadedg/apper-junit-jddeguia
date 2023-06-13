package com.gcash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.security.auth.login.AccountNotFoundException;

public class BalanceServiceTest {
    private BalanceService balanceService;
    private AccountRepository accountRepository;

    @Test
    void getBalanceTest() throws AccountNotFoundException {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId0 = accountRepository.createAccount("JD", 89.9);
        String notExistingId = "non-existing";

        Assertions.assertEquals(89.9, balanceService.getBalance(accountId0));
        Assertions.assertNotNull(balanceService.getBalance(accountId0));
        Assertions.assertThrows(AccountNotFoundException.class, () -> balanceService.getBalance(notExistingId));
    }

    @Test
    void successfulDebit() throws AccountNotFoundException {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId1 = accountRepository.createAccount("JD", 90.0);

        String notExistingId = "non-existing";

        balanceService.debit(accountId1,40.0);

        Assertions.assertEquals(50.0, balanceService.getBalance(accountId1));
        Assertions.assertThrows(AccountNotFoundException.class, () -> balanceService.getBalance(notExistingId));
    }

    @Test
    void successfulCredit() throws AccountNotFoundException {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId = accountRepository.createAccount("JD", 89.9);

        balanceService.credit(accountId, 10.1);

        Assertions.assertEquals(100, balanceService.getBalance(accountId));
    }

    @Test
    void successfulTransfer() throws AccountNotFoundException {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId1 = accountRepository.createAccount("JD1", 90.0);
        String accountId2 = accountRepository.createAccount("JD2", 90.0);

        balanceService.transfer(accountId1, accountId2, 50.0);

        Assertions.assertEquals(40.0, balanceService.getBalance(accountId1));
        Assertions.assertEquals(140.0, balanceService.getBalance(accountId2));
    }
}
