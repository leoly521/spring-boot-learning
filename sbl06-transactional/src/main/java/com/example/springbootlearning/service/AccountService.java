package com.example.springbootlearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootlearning.dao.AccountMapper;
import com.example.springbootlearning.entity.Account;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Transactional(readOnly = false)
    public int add(String name, double money) {
        return this.accountMapper.add(name, money);
    }

    @Transactional(readOnly = false)
    public int update(int id, String name, double money) {
        return this.accountMapper.update(id, name, money);
    }

    @Transactional(readOnly = false)
    public int delete(int id) {
        return this.accountMapper.delete(id);
    }

    public Account getById(int id) {
        return this.accountMapper.getById(id);
    }

    public List<Account> findAll() {
        return this.accountMapper.findAll();
    }

    /**
     * 转账, 由于加了@Transactional注解, 当throwException=true时, 转账不会成功, 则两个人的钱都不会变化
     */
    @Transactional
    public boolean transfer(int fromId, int toId, double amount, boolean throwException) {
        if (amount > 0) {
            Account fromAccount = this.accountMapper.getById(fromId);
            Account toAccount = this.accountMapper.getById(toId);
            if (fromAccount != null && toAccount != null) {
                if (fromAccount.getMoney() > amount) {
                    this.accountMapper.update(fromAccount.getId(), fromAccount.getName(), fromAccount.getMoney() - amount);
                    if (throwException) {
                        throw new RuntimeException("Failed");
                    }
                    this.accountMapper.update(toAccount.getId(), toAccount.getName(), toAccount.getMoney() + amount);
                    return true;
                }
            }
        }
        return false;
    }
}
