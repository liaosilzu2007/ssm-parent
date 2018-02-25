package com.lzumetal.ssm.druid.controller;

import com.google.gson.Gson;
import com.lzumetal.ssm.druid.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-01-22
 */
@RestController
public class BookController {

    private static Gson gson = new Gson();

    @Autowired
    private BookService bookService;


    @RequestMapping("/getBook/{id}")
    String bookInfo(@PathVariable("id") Integer id) {
        return gson.toJson(bookService.getById(id));
    }
}
