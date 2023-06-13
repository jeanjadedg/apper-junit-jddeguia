package com.gcash;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest {
    private AccountRepository repository;

    @BeforeEach
    void setUp() {
        repository = new AccountRepository();
    }
    @Test
    void successfulAccountCreation() {
        // Kick
        String accountId = repository.createAccount("JD", 89.9);

        // Verify
        Assertions.assertEquals(1, repository.getNumberOfAccounts());
        Assertions.assertEquals("JD", repository.getAccount(accountId).getName());
        Assertions.assertNotNull(accountId);
    }

    @Test
    void getAccountTest() {

        String accountId = repository.createAccount("JD", 89.9);

        Assertions.assertEquals("JD", repository.getAccount(accountId).getName());
        Assertions.assertEquals(89.9, repository.getAccount(accountId).getBalance());
        Assertions.assertNull(repository.getAccount("randomized"));

    }

    @Test
    void successfulDeleteAccount() {

        String accountId1 = repository.createAccount("JD1", 100.0);
        String accountId2 = repository.createAccount("JD2", 200.0);

        repository.deleteAccount(accountId1);
        Assertions.assertEquals(1, repository.getNumberOfAccounts());

        repository.deleteAccount(accountId2);
        Assertions.assertEquals(0, repository.getNumberOfAccounts());

        repository.deleteAccount("nonExistingId");
        Assertions.assertEquals(0, repository.getNumberOfAccounts());
    }

    @Test
    void successfulGetNumberAccounts() {

        String accountId0 = repository.createAccount("JD", 89.9);
        String accountId1 = repository.createAccount("JD1", 89.9);
        String accountId2 = repository.createAccount("JD2", 89.9);
        String accountId3 = repository.createAccount("JD3", 89.9);
        String accountId4 = repository.createAccount("JD4", 89.9);

        Assertions.assertEquals(5, repository.getNumberOfAccounts());

        repository.deleteAccount(accountId0);

        Assertions.assertEquals(4, repository.getNumberOfAccounts());

    }

    @Test
    void isAccountsEmpty() {

        Assertions.assertTrue(repository.noRegisteredAccount());

        String accountId0 = repository.createAccount("JD", 89.9);
        Assertions.assertFalse(repository.noRegisteredAccount());

    }
}
