package com.github.cccy0.my.spring.init;

import com.github.cccy0.my.spring.factory.BeanFactory;
import com.github.cccy0.my.spring.ioc.BeanInfo;

import java.lang.reflect.Method;

/**
 * @author Zhai
 * 2020/9/9 16:27
 */

public class AopProxyInstantiates {

    public static void init() {
        for (BeanInfo beanInfo : BeanFactory.getInstance().getBeans()) {
            Object obj = beanInfo.getBean();
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                // todo: 代理aop方法
            }
        }
    }
}
