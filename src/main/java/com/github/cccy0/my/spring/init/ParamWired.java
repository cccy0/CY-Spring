package com.github.cccy0.my.spring.init;

import com.github.cccy0.my.spring.annotation.CYAutowired;
import com.github.cccy0.my.spring.annotation.CYResource;
import com.github.cccy0.my.spring.factory.BeanFactory;
import com.github.cccy0.my.spring.ioc.BeanInfo;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 注入Bean
 * @author Zhai
 * 2020/9/18 15:55
 */

public class ParamWired {
    public static void init() {
        try {
            for (BeanInfo beanInfo : BeanFactory.getInstance().getBeans()) {
                // 代理过的对象里面的属性没有Autowired注解, 所以需要调用getBean()
                Object bean = beanInfo.getBean();
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    // 默认全路径名称
                    String beanName = field.getType().getName();
                    // 如果CYResource指定名称, 则按指定名称注入
                    if (field.isAnnotationPresent(CYResource.class)) {
                        CYResource resource = field.getDeclaredAnnotation(CYResource.class);
                        String value = resource.value();
                        if (!"".equals(value)) {
                            beanName = value;
                        }
                        setProperty(bean, field, beanName);
                    } else if (field.isAnnotationPresent(CYAutowired.class)) {
                        setProperty(bean, field, beanName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setProperty(Object bean, Field field, String beanName) throws IllegalAccessException {
        BeanInfo bi = BeanFactory.getInstance().gainBean(beanName);
        field.set(bean, bi.gainObject());
    }
}
