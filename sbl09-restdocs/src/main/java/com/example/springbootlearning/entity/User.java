package com.example.springbootlearning.entity;

public class User {

    private int id;
    private String username;
    private String fullname;
    private int sex;
    private boolean status;

    public User() {
    }

    public User(int id, String username, String fullname) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
    }

    public User(int id, String username, String fullname, int sex, boolean status) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.sex = sex;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "id:%s, username:%s, fullname:%s, sex:%s, status:%s",
                this.id, this.username, this.fullname, this.sex, this.status);
    }
}
