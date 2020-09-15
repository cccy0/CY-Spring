package com.github.cccy0.my.spring.example.aop;

import com.github.cccy0.my.spring.annotation.CYAopBean;
import com.github.cccy0.my.spring.aop.Aop;

/**
 * @author Zhai
 * 2020/9/9 11:58
 */
@CYAopBean
public class AopTest implements Aop {
    public void before() {
        System.out.println("--- aop before ---");
    }

    public void after(Object object) {
        System.out.println("--- aop after ---");
    }
}
