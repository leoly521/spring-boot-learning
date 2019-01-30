package com.example.springbootlearning.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springbootlearning.entity.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account getByName(String name);

    List<Account> findByMoney(double money);

}
