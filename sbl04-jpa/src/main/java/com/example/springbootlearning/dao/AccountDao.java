package com.example.springbootlearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootlearning.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {
}
