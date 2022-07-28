package com.github.cccy0.my.spring;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.util.Objects;

/**
 * @author Zhai
 * 2022/7/28 10:16
 */

public class CyBootApplication {
    private static final int port = 8080;
    private static final String contextPath = "/";

    public static void main(String[] args) throws LifecycleException, ServletException {
        Tomcat tomcat = new Tomcat();
        String baseDir = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        System.out.println("baseDir: " + baseDir);
        tomcat.setBaseDir(baseDir);
        tomcat.setPort(port);

        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(port);
        tomcat.setConnector(connector);

        tomcat.addWebapp(contextPath, baseDir);
        tomcat.enableNaming();
        tomcat.start();
        tomcat.getServer().await();
    }
}
