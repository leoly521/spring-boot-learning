package com.example.springbootlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootlearning.entity.Book;
import com.example.springbootlearning.service.BookServiceImpl;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping(value = "/{id}")
    public Book getById(@PathVariable(value = "id") int id) {
        return this.bookService.getById(id);
    }

    @PostMapping(value = "/{id}")
    public Book add(@PathVariable(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "price") int price) {
        return this.bookService.add(id, name, price);
    }

    @PutMapping(value = "/{id}")
    public Book update(@PathVariable(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "price") int price) {
        return this.bookService.update(id, name, price);
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") int id) {
        return this.bookService.delete(id);
    }

    @DeleteMapping(value = "")
    public void deleteAll() {
        this.bookService.deleteAll();
    }
}
