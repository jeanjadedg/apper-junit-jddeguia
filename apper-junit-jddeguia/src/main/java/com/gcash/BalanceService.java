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
        return null;
    }

    public void debit(String id, Double amount) {
        Account account = accountRepository.getAccount(id);
        if (account != null) {
            Double currentBalance = account.getBalance();
            if (currentBalance >= account.getBalance()) {
                account.setBalance(currentBalance - amount);
            }
            else {
                System.out.println("Insufficient Balance.");
            }
        }
        else {
            System.out.println("Account not found.");
        }

    }

    public void credit(String id, Double amount) {
        
        Account account = accountRepository.getAccount(id);
        if (account != null) {
            Double currentBalance = account.getBalance();
            account.setBalance(currentBalance + amount);
        }
        else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(String from, String to, Double amount) {

        Account senderAccount = accountRepository.getAccount(from);
        Account receiverAccount = accountRepository.getAccount(to);

        if (senderAccount != null && receiverAccount != null) {
            Double senderBalance = senderAccount.getBalance();
            if (senderBalance >= amount) {
                senderAccount.setBalance(senderBalance - amount);
                receiverAccount.setBalance(receiverAccount.getBalance() + amount);
            }
            else {
                System.out.println("Insufficient balance");
            }
        }
        else {
            System.out.println("Account not found.");
        }
    }
}
