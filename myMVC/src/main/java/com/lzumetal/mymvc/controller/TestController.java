package com.lzumetal.mymvc.controller;

/**
 * <p>Description:</p>
 *
 * @Author：liaosi
 * @Date: 2018-02-25
 */
public class TestController {

    public static void main(String[] args) {
        String s = "abc///xyz";
        System.out.println(s.replaceAll("/+", "/"));
    }
}
