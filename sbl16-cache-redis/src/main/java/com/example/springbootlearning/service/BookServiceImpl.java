package com.example.springbootlearning.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.springbootlearning.entity.Book;

@Service
public class BookServiceImpl {

    private static Map<Integer, Book> books = Collections.synchronizedMap(new HashMap<Integer, Book>());

    static {
        for (int i = 1; i < 10; i++) {
            books.put(i, new Book(i, "Mybook." + i, 100 + i));
        }
    }

    @Cacheable(value = "book", key = "#id", condition = "#id gt 0", unless = "#result eq null")
    // 当参数id的值大于0时，才尝试从缓存取值；并且当参数id的值大于0且返回结果不为null时，才将结果存储至缓存
    // 注意, condition也会作为取缓存的条件，而取缓存时无返回结果，因此Cacheable注解中的condition不能使用#result，
    // 如果condition中写有#result ne null则始终不会尝试取缓存
    public Book getById(int id) {
        System.out.println("getById: " + id);

        if (id <= 0) {
            return null;
        } else if (books.containsKey(id)) {
            return books.get(id);
        } else {
            return null;
        }
    }

    @CachePut(value = "book", key = "#result.id", condition = "#result ne null")
    public Book add(int id, String name, int price) {
        System.out.println("add: " + id);

        if (id <= 0) {
            return null;
        } else if (books.containsKey(id)) {
            throw new RuntimeException("The book already exist. id=" + id);
        } else {
            Book book = new Book(id, "Mybook." + id, price);
            books.put(id, book);
            return book;
        }
    }

    @CachePut(value = "book", key = "#result.id", condition = "#result ne null")
    public Book update(int id, String name, int price) {
        System.out.println("update: " + id);

        if (id <= 0) {
            return null;
        } else if (books.containsKey(id)) {
            Book book = new Book(id, "Mybook." + id, price);
            books.put(id, book);
            return book;
        } else {
            throw new RuntimeException("The book does not exist. id=" + id);
        }
    }

    @CacheEvict(value = "book", key = "#id")
    public boolean delete(int id) {
        System.out.println("delete: " + id);

        return books.remove(id) != null;
    }

    @CacheEvict(value = "book", allEntries = true)
    public void deleteAll() {
        System.out.println("deleteAll:");

        books.clear();
    }
}
