package com.github.cccy0.my.spring.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author Zhai
 * 2020/9/9 16:10
 */

public abstract class AbstractSonProxy implements MethodInterceptor {
    protected Object obj;

    public AbstractSonProxy(Object obj) {
        this.obj = obj;
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
