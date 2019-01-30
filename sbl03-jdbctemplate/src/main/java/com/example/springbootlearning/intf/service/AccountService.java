package com.example.springbootlearning.intf.service;

import java.util.List;

import com.example.springbootlearning.entity.Account;

public interface AccountService {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account getById(int id);

    List<Account> findAll();
}
