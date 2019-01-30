package com.example.springbootlearning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootlearning.dao.AccountDao;
import com.example.springbootlearning.entity.Account;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountDao accountDao;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Account add(@RequestParam(value = "name") String name, @RequestParam(value = "money") double money) {
        Account account = new Account();
        account.setName(name);
        account.setMoney(money);
        account = this.accountDao.saveAndFlush(account);
        return account;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Account update(@PathVariable("id") int id, @RequestParam(value = "name") String name,
                          @RequestParam(value = "money") double money) {
        if (this.accountDao.existsById(id)) {
            Account account = new Account();
            account.setId(id);
            account.setName(name);
            account.setMoney(money);
            account = this.accountDao.saveAndFlush(account);
            return account;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") int id) {
        if (this.accountDao.existsById(id)) {
            this.accountDao.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable("id") int id) {
        Optional<Account> optional = this.accountDao.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Account> findAll() {
        return this.accountDao.findAll();
    }
}
