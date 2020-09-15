package com.github.cccy0.my.spring.annotation;

import java.lang.annotation.*;

/**
 * 指定切面类型以及具体执行类
 * @author Zhai
 * 2020/9/9 11:18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CYAop {
    /**
     * 在之前调用
     */
    String BEFORE = "before";

    /**
     * 在之后调用
     */
    String AFTER = "after";

    /**
     * 环绕调用
     */
    String AROUND = "around";

    String aopBeanName();

    String aopType() default AROUND;
}
