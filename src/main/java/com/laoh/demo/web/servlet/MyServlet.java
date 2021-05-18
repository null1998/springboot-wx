package com.laoh.demo.web.servlet;


import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: Servlet实现
 * @author yanduohuang
 * @date 2020/10/3 18:40
 * @version 1.0
 */
@WebServlet(name = "MyServlet", urlPatterns = "/servlet", asyncSupported = true)
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1559958566705472095L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        AsyncContext asyncContext = request.startAsync();

        asyncContext.start(() -> {
            try {
                response.getWriter().print("hello");
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
