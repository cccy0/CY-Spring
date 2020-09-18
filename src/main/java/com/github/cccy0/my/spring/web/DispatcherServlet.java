package com.github.cccy0.my.spring.web;

import com.github.cccy0.my.spring.init.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Zhai
 * 2020/9/18 11:05
 */

public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            String httpCode = MethodInvoke.invoke(req, resp);
            if ("404".equals(httpCode)) {
                writer.write("404");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        // 类注解扫描
        ClassScanner.doScan();
        // 对象实例化
        BeanInstantiates.init(ClassScanner.classes);
        // 为所有的Bean添加Aop代理对象
        AopProxyInstantiates.init();
        // 关联url与方法
        UrlMethodMapper.init();
        // 属性注入
        ParamWired.init();
    }
}
