package com.example.springbootlearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootlearning.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {


    @ModelAttribute
    protected void checkToken(@RequestHeader(value = "token") String token) {
        Assert.hasLength(token, "'token' must be not empty.");
    }

    @GetMapping(value = "")
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "zhangsan", "张三", 1, true));
        list.add(new User(2, "wangwu", "王五", 1, true));
        return list;
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable(value = "id") int id) {
        return new User(1, "zhangsan", "张三", 1, true);
    }

    @PostMapping(value = "")
    public User add(@RequestParam(value = "username") String username, @RequestParam(value = "fullname") String fullname, @RequestParam(value = "sex") int sex, @RequestParam(value = "status") boolean status) {
        return new User(3, username, fullname, sex, status);
    }

    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") int id, @RequestParam(value = "username") String username, @RequestParam(value = "fullname") String fullname, @RequestParam(value = "sex") int sex, @RequestParam(value = "status") boolean status) {
        return true;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") int id) {
    }
}
