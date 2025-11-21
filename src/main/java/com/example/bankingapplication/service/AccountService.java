package com.example.bankingapplication.service;

import com.example.bankingapplication.DTO.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto findbyid(Long id);
    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    List<AccountDto> findAll();
    void deleteAll();
}
