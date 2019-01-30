package com.example.springbootlearning.entity;

import java.io.Serializable;

public class Account implements Serializable {

    private int id;
    private String name;
    private double money;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
