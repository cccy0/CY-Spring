package com.github.cccy0.my.spring.annotation;

import java.lang.annotation.*;

/**
 * 注解具体执行切面代码的类, 类似于@Aspect注解, 被容器管理
 * @author Zhai
 * 2020/9/9 11:34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CYAopBean {
    /**
     * 自己的Bean名称
     */
    String value() default "";
}
