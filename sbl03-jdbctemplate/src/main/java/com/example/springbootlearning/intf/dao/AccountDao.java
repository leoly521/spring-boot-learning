package com.example.springbootlearning.intf.dao;

import java.util.List;

import com.example.springbootlearning.entity.Account;

public interface AccountDao {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account getById(int id);

    List<Account> findAll();
}
