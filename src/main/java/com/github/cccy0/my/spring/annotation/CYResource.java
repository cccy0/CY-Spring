package com.github.cccy0.my.spring.annotation;

import java.lang.annotation.*;

/**
 * 作用与{@link CYAutowired}类似
 * @author Zhai
 * 2020/9/9 11:37
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CYResource {
    /**
     * 默认按名字注入
     */
    String value() default "";
}
