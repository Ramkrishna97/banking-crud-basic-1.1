package com.example.bankingapplication.mapper;

import com.example.bankingapplication.DTO.AccountDto;
import com.example.bankingapplication.entity.Account;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Accountmapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account=new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account savedAcc) {
        AccountDto dto=new AccountDto(
                savedAcc.getId(),
                savedAcc.getAccountHolderName(),
                savedAcc.getBalance());

        return dto;
    }
}
