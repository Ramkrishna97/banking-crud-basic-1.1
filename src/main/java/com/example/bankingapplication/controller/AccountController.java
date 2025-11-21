package com.example.bankingapplication.controller;

import com.example.bankingapplication.DTO.AccountDto;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.service.AccountService;
import com.example.bankingapplication.service.DropTransaction;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private AccountService service;
    @Autowired
    private DropTransaction drop;
    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(service.createAccount(accountDto), HttpStatus.CREATED);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<AccountDto> findbyId(@PathVariable Long id){
        return new ResponseEntity<>(service.findbyid(id), HttpStatus.OK);
    }

//    @PutMapping("/deposit/{id}/{amount}")
//    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @PathVariable double amount){
//        return new ResponseEntity<>(service.deposit(id,amount), HttpStatus.OK);
//    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String , Double> request ){
                Double amount = request.get("amount");
              AccountDto accountDto =service.deposit(id, amount);
        return ResponseEntity.ok(accountDto);     //-- method originally given
    }
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String , Double> request ){
                Double amount = request.get("amount");
                AccountDto dto= service.withdraw(id, amount);
                return ResponseEntity.ok(dto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> findAll(){
        List<AccountDto> accountDtos= service.findAll();
        return ResponseEntity.ok(accountDtos);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAll(){
        service.deleteAll();
//        drop.dropTable("accounts");
        drop.truncateTable("accounts");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
