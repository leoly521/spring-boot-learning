package com.example.springbootlearning.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootlearning.intf.dao.AccountDao;
import com.example.springbootlearning.entity.Account;
import com.example.springbootlearning.intf.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public int add(Account account) {
        return this.accountDao.add(account);
    }

    @Override
    public int update(Account account) {
        return this.accountDao.update(account);
    }

    @Override
    public int delete(int id) {
        return this.accountDao.delete(id);
    }

    @Override
    public Account getById(int id) {
        return this.accountDao.getById(id);
    }

    @Override
    public List<Account> findAll() {
        return this.accountDao.findAll();
    }
}
