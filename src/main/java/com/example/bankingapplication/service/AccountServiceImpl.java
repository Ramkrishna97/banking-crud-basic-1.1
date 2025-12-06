package com.example.bankingapplication.service;

import com.example.bankingapplication.DTO.AccountDto;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.mapper.Accountmapper;
import com.example.bankingapplication.repository.Accountrepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private Accountrepository repo;
    @Autowired
    public AccountServiceImpl(Accountrepository repo) {
        this.repo = repo;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= Accountmapper.mapToAccount(accountDto);
        Account savedAcc=repo.save(account);
        return Accountmapper.mapToAccountDto(savedAcc);
    }

    @Override
    public AccountDto findbyid(Long id) {
        Account account= repo.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        return Accountmapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account= repo
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));
        Double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAcc=repo.save(account);
//        repo.save(account);       -- why can't use this ?
        return Accountmapper.mapToAccountDto(savedAcc);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= repo               // check if account with id present or not
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));
        if(account.getBalance()<amount) {
            throw new RuntimeException("Insufficient balance");
        } // check for sufficient balance
        Double total=account.getBalance()-amount;       // total amount to be deduct from balance
        account.setBalance(total);                      // set new balance after withdrawal
        Account savedAcc=repo.save(account);            // save updated account to repository
        return Accountmapper.mapToAccountDto(savedAcc);
    }

    @Override
    public List<AccountDto> findAll() {
        List<Account> accounts=repo.findAll();
        return accounts.stream().map(Accountmapper::mapToAccountDto).toList();
    }
    @Override
    public void deleteAll() {
        repo.deleteAll();
    }


}
