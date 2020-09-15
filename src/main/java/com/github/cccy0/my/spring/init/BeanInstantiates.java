package com.github.cccy0.my.spring.init;

import com.github.cccy0.my.spring.annotation.CYController;
import com.github.cccy0.my.spring.ioc.BeanInfo;

import java.util.List;

/**
 * 实例化所有Bean
 * @author Zhai
 * 2020/9/15 18:04
 */

public class BeanInstantiates {

    public static void init(List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
           boolean flag = false;
           BeanInfo bi = new BeanInfo();
           if (clazz.isAnnotationPresent(CYController.class)) {
               flag = true;
               CYController controller = clazz.getAnnotation(CYController.class);
               // todo: 获取Bean 名称, 并构造
               String value = controller.value();
           }
        }
    }
}
