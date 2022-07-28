package com.github.cccy0.my.spring.example.controller;

import com.github.cccy0.my.spring.factory.URLFactory;
import com.github.cccy0.my.spring.web.DispatcherServlet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Zhai
 * 2022/7/28 14:04
 */

public class TestControllerTest {
    DispatcherServlet dispatcherServlet;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Before
    public void before() throws Exception {
        dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void helloWorld() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {

        Mockito.when(httpServletRequest.getRequestURI()).thenReturn("/test/people");
        Mockito.when(httpServletRequest.getParameter("name")).thenReturn("张三");
        Mockito.when(httpServletRequest.getParameter("remark")).thenReturn("111");

        Writer stringWriter = new StringWriter();
        Mockito.when(httpServletResponse.getWriter()).thenReturn(new PrintWriter(stringWriter));

        Method method = DispatcherServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        method.setAccessible(true);
        method.invoke(dispatcherServlet, httpServletRequest, httpServletResponse);


        Assert.assertEquals(stringWriter.toString(), "Hello 张三!(age-23)");

    }
}