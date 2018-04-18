package com.lzumetal.ssm.paramcheck.controller;

import com.google.gson.Gson;
import com.lzumetal.ssm.paramcheck.annotation.NotNull;
import com.lzumetal.ssm.paramcheck.annotation.ValidParam;
import com.lzumetal.ssm.paramcheck.requestParam.StudentParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private static Gson gson = new Gson();

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public StudentParam checkParam(@ValidParam StudentParam param, @NotNull Integer limit) {
        System.out.println(gson.toJson(param));
        System.out.println(limit);
        return param;
    }

}
