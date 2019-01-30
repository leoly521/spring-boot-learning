package com.example.springbootlearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootlearning.dao.AccountMapper;
import com.example.springbootlearning.entity.Account;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public int add(String name, double money) {
        return this.accountMapper.add(name, money);
    }
    public int update(int id, String name, double money) {
        return this.accountMapper.update(id, name, money);
    }
    public int delete(int id) {
        return this.accountMapper.delete(id);
    }
    public Account getById(int id) {
        return this.accountMapper.getById(id);
    }
    public List<Account> findAll() {
        return this.accountMapper.findAll();
    }
}
