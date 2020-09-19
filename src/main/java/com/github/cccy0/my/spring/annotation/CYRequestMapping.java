package com.github.cccy0.my.spring.annotation;

import java.lang.annotation.*;

/**
 * 指定映射Path
 * @author Zhai
 * 2020/9/9 11:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CYRequestMapping {
    String METHOD_DEFAULT = "NONE";
    String METHOD_GET = "GET";
    String METHOD_POST = "POST";

    String value() default "";

    String method() default METHOD_DEFAULT;
}
