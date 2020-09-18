package com.github.cccy0.my.spring.init;

import com.github.cccy0.my.spring.annotation.CYRequestMapping;
import com.github.cccy0.my.spring.factory.BeanFactory;
import com.github.cccy0.my.spring.factory.URLFactory;
import com.github.cccy0.my.spring.ioc.BeanInfo;
import com.github.cccy0.my.spring.ioc.UrlMethodRelate;

import java.lang.reflect.Method;

/**
 * url与方法映射
 * @author Zhai
 * 2020/9/18 14:39
 */

public class UrlMethodMapper {

    public static void init() {
        for (BeanInfo beanInfo : BeanFactory.getInstance().gainControllers()) {
            Object bean = beanInfo.getBean();
            String baseUrl = "";
            if (bean.getClass().isAnnotationPresent(CYRequestMapping.class)) {
                CYRequestMapping requestMapping = bean.getClass().getAnnotation(CYRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            urlRelateMethodProcess(bean, baseUrl);
        }
    }

    private static void urlRelateMethodProcess(Object bean, String baseUrl) {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CYRequestMapping.class)) {
                CYRequestMapping requestMapping = method.getAnnotation(CYRequestMapping.class);
                String methodUrl = requestMapping.value();
                UrlMethodRelate urlMethodRelate = new UrlMethodRelate();
                urlMethodRelate.setUrl(baseUrl + methodUrl);
                // 保存的是全路径
                urlMethodRelate.setClassName(bean.getClass().getName());
                urlMethodRelate.setMethodName(method.getName());
                URLFactory.getInstance().addUrlMethodRelate(urlMethodRelate);
            }
        }
    }
}
