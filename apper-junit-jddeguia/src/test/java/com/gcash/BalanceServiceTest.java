package com.gcash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BalanceServiceTest {
    private BalanceService balanceService;
    private AccountRepository accountRepository;

    @Test
    void getBalanceTest() {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId0 = accountRepository.createAccount("JD", 89.9);

        Assertions.assertEquals(89.9, balanceService.getBalance(accountId0));
        Assertions.assertNotNull(accountId0);
    }

    @Test
    void successfulDebit() {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId1 = accountRepository.createAccount("JD", 90.0);

        balanceService.debit(accountId1,40.0);

        Assertions.assertEquals(50.0, balanceService.getBalance(accountId1));
    }

    @Test
    void successfulCredit() {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId = accountRepository.createAccount("JD", 89.9);

        balanceService.credit(accountId, 10.1);

        Assertions.assertEquals(100, balanceService.getBalance(accountId));
    }

    @Test
    void successfulTransfer() {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);

        String accountId1 = accountRepository.createAccount("JD1", 90.0);
        String accountId2 = accountRepository.createAccount("JD2", 90.0);

        balanceService.transfer(accountId1, accountId2, 50.0);

        Assertions.assertEquals(40.0, balanceService.getBalance(accountId1));
        Assertions.assertEquals(140.0, balanceService.getBalance(accountId2));
    }
}
