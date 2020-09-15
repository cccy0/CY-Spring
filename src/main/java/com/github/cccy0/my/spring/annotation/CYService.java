package com.github.cccy0.my.spring.annotation;

import java.lang.annotation.*;

/**
 * @author Zhai
 * 2020/9/9 11:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CYService {
    String value();
}
