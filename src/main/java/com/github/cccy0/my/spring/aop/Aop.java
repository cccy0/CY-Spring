package com.github.cccy0.my.spring.aop;

/**
 * @author Zhai
 * 2020/9/9 11:56
 */

public interface Aop {
    void before();

    /**
     * @param object 方法执行的返回值
     */
    void after(Object object);
}
