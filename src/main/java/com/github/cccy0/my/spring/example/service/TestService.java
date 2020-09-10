package com.github.cccy0.my.spring.example.service;

import com.github.cccy0.my.spring.annotation.CYAop;
import com.github.cccy0.my.spring.annotation.CYService;

/**
 * @author Zhai
 * 2020/9/9 14:17
 */
@CYService
public class TestService {

    @CYAop(aopBeanName = "aopTest", aopType = CYAop.AROUND)
    public String getPeople(String name) {
        if (name == null || "".equals(name)) {
            return "No Name:age-?,sex-?";
        }
        switch (name) {
            case "张三":
                return "Hello " + name + "!(age-23,sex-mail)";
            case "李四":
                return "Hello " + name + "!(age-24,sex-femail)";
            case "王五":
                return "Hello " + name + "!(age-27,sex-femail)";
            default:
                return "Who are you?";
        }
    }
}
