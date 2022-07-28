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
        req.setCharacterEncoding("utf-8");
        // 一定要先设置编码再获取writer, 因为writer要指定编码
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            String httpCode = MethodInvoke.invoke(req, resp);
            if ("404".equals(httpCode)) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                writer = resp.getWriter();
                writer.write("{\"code\": 404}");
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
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
        System.out.println("bean init success");
    }
}
