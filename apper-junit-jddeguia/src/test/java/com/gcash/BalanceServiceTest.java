package com.gcash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BalanceServiceTest {
    private BalanceService balanceService;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);
    }


    @Test
    void getBalanceTest() throws com.gcash.AccountNotFoundException {

        String accountId0 = accountRepository.createAccount("JD", 89.9);

        String notExistingId = "non-existing";

        Assertions.assertEquals(89.9, balanceService.getBalance(accountId0));
        Assertions.assertNotNull(balanceService.getBalance(accountId0));
        Assertions.assertThrows(com.gcash.AccountNotFoundException.class, () -> balanceService.getBalance(notExistingId));
    }

    @Test
    void successfulDebit() throws com.gcash.AccountNotFoundException {

        String accountId1 = accountRepository.createAccount("JD", 90.0);

        String notExistingId = "non-existing";

        balanceService.debit(accountId1,40.0);

        Assertions.assertEquals(50.0, balanceService.getBalance(accountId1));
        Assertions.assertThrows(com.gcash.AccountNotFoundException.class, () -> balanceService.debit(notExistingId, 50.0));
    }

    @Test
    void successfulCredit() throws com.gcash.AccountNotFoundException {

        String accountId = accountRepository.createAccount("JD", 89.9);

        String notExistingId = "non-existing";


        balanceService.credit(accountId, 10.1);

        Assertions.assertEquals(100, balanceService.getBalance(accountId));
        Assertions.assertThrows(com.gcash.AccountNotFoundException.class, () -> balanceService.credit(notExistingId, 50.0));
    }

    @Test
    void successfulTransfer() throws com.gcash.AccountNotFoundException {

        String accountId1 = accountRepository.createAccount("JD1", 90.0);
        String accountId2 = accountRepository.createAccount("JD2", 90.0);

        balanceService.transfer(accountId1, accountId2, 50.0);

        Assertions.assertEquals(40.0, balanceService.getBalance(accountId1));
        Assertions.assertEquals(140.0, balanceService.getBalance(accountId2));
    }
}
