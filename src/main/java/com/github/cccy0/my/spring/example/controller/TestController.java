package com.github.cccy0.my.spring.example.controller;

import com.github.cccy0.my.spring.annotation.CYAutowired;
import com.github.cccy0.my.spring.annotation.CYController;
import com.github.cccy0.my.spring.annotation.CYRequestMapping;
import com.github.cccy0.my.spring.annotation.CYResource;
import com.github.cccy0.my.spring.example.service.TestService;

/**
 * @author Zhai
 */
@CYController
@CYRequestMapping("/test")
public class TestController {

    @CYAutowired
    private TestService testService;

    @CYRequestMapping(value = "/people", method = CYRequestMapping.METHOD_GET)
    public String helloWorld(String name, String remark) {
        System.out.println(name);
        System.out.println(remark);
        return testService.getPeople(name);
    }

}
