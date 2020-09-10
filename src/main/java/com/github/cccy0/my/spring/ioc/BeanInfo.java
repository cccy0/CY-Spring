package com.github.cccy0.my.spring.ioc;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 存储Bean信息
 * @author Zhai
 * 2020/9/9 14:02
 */

public class BeanInfo {
    /**
     * bean的Type
     */
    public static final String CONTROLLER = "controller";
    public static final String SERVICE = "service";
    public static final String AOP = "aop";

    // 别名
    private String name;

    // 类名
    private String className;

    // bean的类型
    private String beanType;

    // aop对象
    private Object aop;

    // 对象
    private Object bean;

    private List<MethodInfo> methods;

    public Object gainObject() {
        return gainInvokeObj();
    }

    public Object gainInvokeObj() {
        return aop == null ? bean : aop;
    }

    public BeanInfo gainBeanInfo(String target){
        if (name != null && name.equals(target)) return this;
        if (className != null && className.equals(target)) return this;
        return null;
    }

    public boolean isController() {
        return CONTROLLER.equals(this.beanType);
    }

    public boolean isService() {
        return SERVICE.equals(this.beanType);
    }

    public boolean isAop() {
        return AOP.equals(this.beanType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public Object getAop() {
        return aop;
    }

    public void setAop(Object aop) {
        this.aop = aop;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }

    public static class MethodInfo{

        private List<Class<?>> paramTypes;

        private List<String> paramNames;

        private String methodName;

        private Method method;

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public List<Class<?>> getParamTypes() {
            return paramTypes;
        }

        public void setParamTypes(List<Class<?>> paramTypes) {
            this.paramTypes = paramTypes;
        }

        public List<String> getParamNames() {
            return paramNames;
        }

        public void setParamNames(List<String> paramNames) {
            this.paramNames = paramNames;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }
    }
}
