package com.github.cccy0.my.spring.ioc;

/**
 * url映射method
 **/
public class UrlMethodRelate {

    private String url;

    private String className;

    private String methodName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
