package com.github.cccy0.my.spring.example.aop;

import com.github.cccy0.my.spring.annotation.CYAopBean;
import com.github.cccy0.my.spring.aop.Aop;

/**
 * @author Zhai
 * 2020/9/9 11:58
 */
@CYAopBean
public class AopTest implements Aop<String> {
    public void before() {
        System.out.println("--- aop before ---");
    }

    public String after(String object) {
        System.out.println("--- aop after ---");
        return object + "--- aop after";
    }
}
