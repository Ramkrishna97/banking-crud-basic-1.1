package com.example.bankingapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AccountDto {
    private long id;
    private String accountHolderName;
    private double balance;


    public long getId() {
        return id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public AccountDto(long id, String accountHolderName, double balance) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
