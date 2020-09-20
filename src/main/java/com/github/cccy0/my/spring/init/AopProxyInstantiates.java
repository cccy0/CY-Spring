package com.github.cccy0.my.spring.init;

import com.github.cccy0.my.spring.annotation.CYAop;
import com.github.cccy0.my.spring.aop.Aop;
import com.github.cccy0.my.spring.factory.BeanFactory;
import com.github.cccy0.my.spring.ioc.BeanInfo;
import com.github.cccy0.my.spring.proxy.AbstractSonProxy;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 把所有的Bean都进行代理
 * @author Zhai
 * 2020/9/9 16:27
 */

public class AopProxyInstantiates {

    public static void init() {
        for (BeanInfo beanInfo : BeanFactory.getInstance().getBeans()) {
            Object obj = beanInfo.getBean();
            // 获取所有方法
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(CYAop.class)) {
                    // 对方法进行代理
                    delegate(obj, method);
                }
            }
            if (obj.getClass().isAnnotationPresent(CYAop.class)) {
                // 对类进行代理
                delegate(obj, null);
            }
        }
    }

    private static void delegate(final Object obj, final Method method) {
        // 取Aop注解
        CYAop aop;
        if (method != null) {
            aop = method.getAnnotation(CYAop.class);
        } else {
            aop = obj.getClass().getAnnotation(CYAop.class);
        }
        String aopBeanName = aop.aopBeanName();
        String aopType = aop.aopType();
        // 获取Bean
        BeanInfo beanInfo = BeanFactory.getInstance().gainBean(aopBeanName);
        // 不能对Aop bean 进行代理 所以这里直接获取原生Bean
        final Aop aopBean = (Aop) beanInfo.getBean();
        // 生成代理对象
        Object proxy = new AbstractSonProxy(obj) {

            /**
             * @return Object 代理之后的方法返回值
             */
            @Override
            public Object intercept(Object o, Method m, Object[] args, MethodProxy methodProxy) throws Throwable {
                return getObject(m, args);
            }

            /**
             * 执行切面方法和方法, 并返回方法返回值
             */
            private Object getObject(Method m, Object[] args) throws InvocationTargetException, IllegalAccessException {
                switch (aopType) {
                    case CYAop.BEFORE:
                        aopBean.before();
                        return m.invoke(obj, args);
                    case CYAop.AFTER: {
                        Object result = m.invoke(obj, args);
                        aopBean.after(result);
                        return result;
                    }
                    case CYAop.AROUND: {
                        aopBean.before();
                        Object result = m.invoke(obj, args);
                        aopBean.after(result);
                        return result;
                    }
                    default:
                        return m.invoke(obj, args);
                }
            }
        }.getProxy();
        // 设置Bean的Aop对象
        BeanFactory.getInstance().gainBean(obj.getClass().getName()).setAop(proxy);
    }
}
