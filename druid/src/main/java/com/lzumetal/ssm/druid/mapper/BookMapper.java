package com.lzumetal.ssm.druid.mapper;


import com.lzumetal.ssm.druid.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    int insert(Book record);
    List<Book> selectAll();
    Book getById(@Param(value = "id") Integer id);
}