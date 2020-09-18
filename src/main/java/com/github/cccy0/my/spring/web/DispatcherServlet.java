package com.github.cccy0.my.spring.web;

import com.github.cccy0.my.spring.factory.BeanFactory;
import com.github.cccy0.my.spring.factory.URLFactory;
import com.github.cccy0.my.spring.init.AopProxyInstantiates;
import com.github.cccy0.my.spring.init.BeanInstantiates;
import com.github.cccy0.my.spring.init.ClassScanner;
import com.github.cccy0.my.spring.init.UrlMethodMapper;
import com.github.cccy0.my.spring.ioc.BeanInfo;

import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * @author Zhai
 * 2020/9/18 11:05
 */

public class DispatcherServlet /*extends HttpServlet**/ {
    public static void main(String[] args) {
        // 类注解扫描
        ClassScanner.doScan();
        // 对象实例化
        BeanInstantiates.init(ClassScanner.classes);
        // 为所有的Bean添加Aop代理对象
        AopProxyInstantiates.init();

        UrlMethodMapper.init();

        URLFactory instance = URLFactory.getInstance();
        System.out.println("");
    }
}
