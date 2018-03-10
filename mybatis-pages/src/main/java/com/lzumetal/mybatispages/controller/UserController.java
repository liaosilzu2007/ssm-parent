package com.lzumetal.mybatispages.controller;

import com.lzumetal.mybatispages.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-03-10
 */
@RestController(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

}
