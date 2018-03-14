package com.lzumetal.mybatispages.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzumetal.mybatispages.entity.po.User;
import com.lzumetal.mybatispages.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-03-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mybatis-config.xml", "classpath:spring-context.xml", "classpath:spring-mvc.xml"})
public class MainTest {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private UserService userService;

    @Before
    public void init() {
    }

    @Test
    public void testReturnMap() {
        Map<Long, User> map = userService.getById(10L);
        System.out.println(map);
    }

    @Test
    public void testPagination() {
        List<User> users = userService.getByPage("2017-05-01",1, 10);
        System.out.println(gson.toJson(users));
    }


}
