package com.gcash;

public class Account {

    private final String id;
    private final String name;
    private Double  balance;

    public Account(String id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
