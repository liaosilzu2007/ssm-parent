package com.lzumetal.springmvc.multiservlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-01-19
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {


    @RequestMapping(value = "/testMultiServlet", method = RequestMethod.GET)
    public String testMultiServlet() {
        return "from controller";
    }

}
