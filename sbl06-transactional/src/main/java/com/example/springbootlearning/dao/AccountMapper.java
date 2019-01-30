package com.example.springbootlearning.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.springbootlearning.entity.Account;

public interface AccountMapper {

    int add(@Param("name") String name, @Param("money") double money);

    int update(@Param("id") int id, @Param("name") String name, @Param("money") double money);

    int delete(int id);

    Account getById(@Param("id") int id);

    List<Account> findAll();
}
