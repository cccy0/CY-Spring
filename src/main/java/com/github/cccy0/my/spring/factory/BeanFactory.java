package com.github.cccy0.my.spring.factory;

import com.github.cccy0.my.spring.ioc.BeanInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhai
 * 2020/9/9 15:42
 */

public class BeanFactory {
    private List<BeanInfo> beans = new ArrayList<>();

    private BeanFactory() {
    }

    /**
     * 获取Bean
     */
    public BeanInfo gainBean(String target) {
        for (BeanInfo b : getBeans()) {
            BeanInfo beanInfo = b.gainBeanInfo(target);
            if (beanInfo != null) {
                return beanInfo;
            }
        }
        return null;
    }

    /**
     * 获取所有Bean
     * @return beans
     */
    public List<BeanInfo> getBeans() {
        return beans;
    }

    /**
     * 获取所有Controller
     */
    public List<BeanInfo> gainControllers() {
        List<BeanInfo> controllers = new ArrayList<>();
        for (BeanInfo b : getBeans()) {
            if (b.isController()) {
                controllers.add(b);
            }
        }
        return controllers;
    }

    /**
     * 获取所有Service
     */
    public List<BeanInfo> gainServices() {
        List<BeanInfo> services = new ArrayList<>();
        for (BeanInfo b : beans) {
            if (b.isService()) {
                services.add(b);
            }
        }
        return services;
    }

    /**
     * 获取所有Aop
     */
    public List<BeanInfo> gainAops() {
        List<BeanInfo> aops = new ArrayList<>();
        for (BeanInfo b : beans) {
            if (b.isAop()) {
                aops.add(b);
            }
        }
        return aops;
    }

    public boolean addBean(BeanInfo beanInfo) {
        return beans.add(beanInfo);
    }

    private static class Singleton {
        private static final BeanFactory instance = new BeanFactory();
    }

    public static BeanFactory getInstance() {
        return Singleton.instance;
    }
}
