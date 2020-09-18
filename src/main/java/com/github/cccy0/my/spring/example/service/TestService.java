package com.github.cccy0.my.spring.example.service;

import com.github.cccy0.my.spring.annotation.CYAop;
import com.github.cccy0.my.spring.annotation.CYAutowired;
import com.github.cccy0.my.spring.annotation.CYService;
import com.github.cccy0.my.spring.example.dao.TestDao;

/**
 * @author Zhai
 * 2020/9/9 14:17
 */
@CYService
public class TestService {
    @CYAutowired
    private TestDao testDao;

    @CYAop(aopBeanName = "aopTest")
    public String getPeople(String name) {
        if (name == null || "".equals(name)) {
            return "No Name:age-?";
        }
        switch (name) {
            case "张三":
                return "Hello " + name + "!(age-23)";
            case "李四":
                return "Hello " + name + "!(age-24)";
            case "王五":
                return "Hello " + name + "!(age-27)";
            default:
                return testDao.getData();
        }
    }
}
