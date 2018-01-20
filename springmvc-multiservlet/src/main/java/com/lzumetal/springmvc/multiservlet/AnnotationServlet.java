package com.lzumetal.springmvc.multiservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类描述：通过注解的方式也可以配置servlet
 * 创建人：liaosi
 * 创建时间：2018年01月20日
 */
@WebServlet(name = "annoationServlet", urlPatterns = "/test/testAnnotationServlet")
public class AnnotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().print("from the Servlet by annotation");
    }
}
