package com.gcash;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest {

    @Test
    void successfulAccountCreation() {
        // Setup
        AccountRepository repository = new AccountRepository();

        // Kick
        String accountId = repository.createAccount("JD", 89.9);

        // Verify
        Assertions.assertEquals(1, repository.getNumberOfAccounts());
        Assertions.assertEquals("JD", repository.getAccount(accountId).name());
        Assertions.assertNotNull(accountId);
    }

    @Test
    void getAccountTest() {
        AccountRepository repository = new AccountRepository();

        String accountId = repository.createAccount("JD", 89.9);

        Assertions.assertEquals("JD", repository.getAccount(accountId).name());
        Assertions.assertEquals(89.9, repository.getAccount(accountId).balance());
        Assertions.assertEquals(null, repository.getAccount("randomid"));

    }

    @Test
    void successfulDeleteAccount() {
        AccountRepository repository = new AccountRepository();

        String id = repository.createAccount("JD", 89.9);

        repository.deleteAccount(id);

        Assertions.assertEquals(0, repository.getNumberOfAccounts());
    }

    @Test
    void successfulGetNumberAccounts() {
        AccountRepository repository = new AccountRepository();

        String accountId0 = repository.createAccount("JD", 89.9);
        String accountId1 = repository.createAccount("JD", 89.9);
        String accountId2 = repository.createAccount("JD", 89.9);
        String accountId3 = repository.createAccount("JD", 89.9);
        String accountId4 = repository.createAccount("JD", 89.9);

        Assertions.assertEquals(5, repository.getNumberOfAccounts());

    }

}
