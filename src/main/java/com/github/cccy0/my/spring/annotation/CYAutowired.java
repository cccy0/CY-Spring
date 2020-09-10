package com.github.cccy0.my.spring.annotation;

import java.lang.annotation.*;

/**
 * 表示Field是需要注入的, 按属性注入
 * @author Zhai
 * 2020/9/9 11:37
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CYAutowired {
}
