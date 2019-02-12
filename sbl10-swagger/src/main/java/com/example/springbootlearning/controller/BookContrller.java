package com.example.springbootlearning.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
@RequestMapping(value = "/book")
public class BookContrller {

    private static Map<Integer, Book> books = Collections.synchronizedMap(new HashMap<Integer, Book>());

    static {
        for (int i = 1; i < 10; i++) {
            books.put(i, new Book(i, "Book" + i, 10 + i));
        }
    }

    @ModelAttribute
    protected void checkToken(@RequestHeader(value = "token") String token) {
        Assert.hasLength(token, "'token' must be not empty.");
    }

    @ApiOperation(value = "获取图书列表", produces = MediaType.APPLICATION_JSON_VALUE, notes = "返回所有图书的列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", required = true, dataType = "String", paramType = "header")
    })
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<Book> findAll() {
        List<Book> book = new ArrayList<>(this.books.values());
        return book;
    }

    @ApiOperation(value = "创建图书", produces = MediaType.APPLICATION_JSON_VALUE, notes = "创建一本图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "book", value = "图书详细实体", required = true, dataType = "Book")
    })
    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public boolean add(@RequestBody Book book) {
        if (book != null && book.getId() > 0 && StringUtils.hasText(book.getName()) && !books.containsKey(book.getId())) {
            this.books.put(book.getId(), book);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "获取图书", produces = MediaType.APPLICATION_JSON_VALUE, notes = "根据图书ID获取指定图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public Book getById(@PathVariable(value = "id") int id) {
        return this.books.get(id);
    }

    @ApiOperation(value = "更新图书", produces = MediaType.APPLICATION_JSON_VALUE, notes = "根据图书ID更新指定图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "book", value = "图书实体book", required = true, dataType = "Book")
    })
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    public boolean update(@PathVariable(value = "id") int id, @RequestBody Book book) {
        if (books.containsKey(id) && book.getId() > 0 && !books.containsKey(book.getId())) {
            this.books.remove(id);
            this.books.put(book.getId(), book);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "删除图书", produces = MediaType.APPLICATION_JSON_VALUE, notes = "根据图书ID删除指定图书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "图书ID", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        this.books.remove(id);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = {"/hi"}, method = RequestMethod.GET)
    public String jsonTest() {
        return "Hello word!";
    }
}
