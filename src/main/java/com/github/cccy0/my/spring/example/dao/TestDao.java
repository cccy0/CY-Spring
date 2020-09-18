package com.github.cccy0.my.spring.example.dao;

import com.github.cccy0.my.spring.annotation.CYService;

/**
 * @author Zhai
 * 2020/9/18 16:52
 */
@CYService
public class TestDao {
    private static final String DATA = "{\"id\": 1, \"name\": \"张三\"}";

    public String getData() {
        return DATA;
    }
}
