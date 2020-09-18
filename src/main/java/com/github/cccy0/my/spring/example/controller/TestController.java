package com.github.cccy0.my.spring.example.controller;

import com.github.cccy0.my.spring.annotation.CYController;
import com.github.cccy0.my.spring.annotation.CYRequestMapping;
import com.github.cccy0.my.spring.annotation.CYResource;
import com.github.cccy0.my.spring.example.service.TestService;

/**
 *
 **/
@CYController
@CYRequestMapping("/test")
public class TestController {

    @CYResource
    private TestService testService;

    @CYRequestMapping("/people")
    public String helloWorld(String name) {
        return testService.getPeople(name);
    }

}
