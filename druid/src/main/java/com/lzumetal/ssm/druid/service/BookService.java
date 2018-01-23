package com.lzumetal.ssm.druid.service;

import com.lzumetal.ssm.druid.entity.Book;
import com.lzumetal.ssm.druid.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-01-22
 */
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getAllBooks() {
        return bookMapper.selectAll();
    }

    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }
}
