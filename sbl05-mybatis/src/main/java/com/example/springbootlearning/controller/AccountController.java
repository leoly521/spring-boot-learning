package com.example.springbootlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootlearning.entity.Account;
import com.example.springbootlearning.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean add(@RequestParam(value = "name") String name, @RequestParam(value = "money") double money) {
        int t = this.accountService.add(name, money);
        return t == 1;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@PathVariable("id") int id, @RequestParam(value = "name") String name,
                          @RequestParam(value = "money") double money) {
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setMoney(money);
        int t = this.accountService.update(id, name, money);
        return t == 1;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") int id) {
        int t = this.accountService.delete(id);
        return t == 1;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getById(@PathVariable("id") int id) {
        return this.accountService.getById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Account> findAll() {
        return this.accountService.findAll();
    }
}
