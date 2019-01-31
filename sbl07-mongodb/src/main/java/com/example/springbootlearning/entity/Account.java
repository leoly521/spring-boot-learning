package com.example.springbootlearning.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Account implements Serializable {

    @Id
    private String id;
    private String name;
    private double money;

    public Account() {
    }

    public Account(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return String.format(
                "id:%s, firstName:%s, lastName:%s",
                this.id, this.name, this.money);
    }
}
