package com.example.springbootlearning.entity;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String name;
    private double price;

    public Book() {
    }

    public Book(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "id:%s, name:%s, price:%s",
                this.id, this.name, this.price);
    }
}
