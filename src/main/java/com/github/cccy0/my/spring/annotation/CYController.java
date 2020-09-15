package com.github.cccy0.my.spring.annotation;

import java.lang.annotation.*;

/**
 * Controller类, 被容器管理
 * @author Zhai
 * 2020/9/9 11:43
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CYController {
    String value();
}
