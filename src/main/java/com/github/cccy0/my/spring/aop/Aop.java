package com.github.cccy0.my.spring.aop;

/**
 * @author Zhai
 * 2020/9/9 11:56
 */

public interface Aop<T> {
    void before();

    T after(T object);
}
