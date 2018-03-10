package com.lzumetal.mybatispages.test;

import com.lzumetal.mybatispages.entity.po.User;
import com.lzumetal.mybatispages.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-03-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:db-config.properties", "classpath:mybatis-config.xml",
        "classpath:spring-context.xml", "classpath:spring-mvc.xml"})
public class MainTest {

    @Autowired
    private UserService userService;

    @Test
    public void test2() {
        Map<Long, User> userMap = userService.getById(5L);
        System.out.println(userMap);
    }


}
