package com.gcash;

public class BalanceService {
    private final AccountRepository accountRepository;

    public BalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Double getBalance(String id) {
        Account account = accountRepository.getAccount(id);
        if (account != null) {
            return account.getBalance();
        }
        else {
            return null;
        }
    }

    public void debit(String id, Double amount) {
        Account account = accountRepository.getAccount(id);
        if (account != null) {
            Double currentBalance = account.getBalance();
            if (currentBalance >= account.getBalance()) {
                account.setBalance(currentBalance - amount);
            }
        }
    }

    public void credit(String id, Double amount) {
        
        Account account = accountRepository.getAccount(id);
        if (account != null) {
            Double currentBalance = account.getBalance();
            account.setBalance(currentBalance + amount);
        }
    }

    public void transfer(String from, String to, Double amount) {
        Account senderAccount = accountRepository.getAccount(from);

        if (from != null && to != null && senderAccount.getBalance() > amount) {
            debit(from, amount);
            credit(to, amount);
        }
    }
}
