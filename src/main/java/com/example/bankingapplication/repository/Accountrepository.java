package com.example.bankingapplication.repository;

import com.example.bankingapplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Accountrepository extends JpaRepository<Account, Long> {
}
