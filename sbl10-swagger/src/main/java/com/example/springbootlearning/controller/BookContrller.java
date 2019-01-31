package com.example.springbootlearning.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootlearning.entity.Book;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户创建某本图书	POST	/books/
 * 用户修改对某本图书	PUT	/books/:id/
 * 用户删除对某本图书	DELETE	/books/:id/
 * 用户获取所有的图书 GET /books
 * 用户获取某一图书  GET /Books/:id
 * 官方文档：http://swagger.io/docs/specification/api-host-and-base-path/
 */
@RestController
@RequestMapping(value = "/books")
public class BookContrller {

    Map<Integer, Book> books = Collections.synchronizedMap(new HashMap<Integer, Book>());

    @ApiOperation(value = "获取图书列表", notes = "返回所有图书的列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Book> findAll() {
        List<Book> book = new ArrayList<>(this.books.values());
        return book;
    }

    @ApiOperation(value = "创建图书", notes = "创建一本图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "book", value = "图书详细实体", required = true, dataType = "Book")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean add(@RequestBody Book book) {
        this.books.put(book.getId(), book);
        return true;
    }

    @ApiOperation(value = "获取图书", notes = "根据图书ID获取指定图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getById(@PathVariable Long id) {
        return this.books.get(id);
    }

    @ApiOperation(value = "更新图书", notes = "根据图书ID更新指定图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "book", value = "图书实体book", required = true, dataType = "Book")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public boolean update(@PathVariable int id, @RequestBody Book book) {
        Book book1 = this.books.get(id);
        book1.setName(book.getName());
        book1.setPrice(book.getPrice());
        this.books.put(id, book1);
        return true;
    }

    @ApiOperation(value = "删除图书", notes = "根据图书ID删除指定图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        this.books.remove(id);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String jsonTest() {
        return "Hello word!";
    }
}
