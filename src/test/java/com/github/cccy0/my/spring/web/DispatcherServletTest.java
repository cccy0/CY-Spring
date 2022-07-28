package com.github.cccy0.my.spring.web;

import com.github.cccy0.my.spring.web.DispatcherServlet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author Zhai
 * 2022/7/28 11:23
 */
@RunWith(MockitoJUnitRunner.class)
public class DispatcherServletTest {
    DispatcherServlet dispatcherServlet;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;


    @Before
    public void setUp() throws Exception {
        dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.init();
    }

    @After
    public void tearDown() throws Exception {
        dispatcherServlet.destroy();
        dispatcherServlet = null;
    }

    @Test
    public void doGet() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = DispatcherServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        method.setAccessible(true);
        method.invoke(dispatcherServlet, httpServletRequest, httpServletResponse);
    }

    @Test
    public void doPost() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = DispatcherServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
        method.setAccessible(true);
        method.invoke(dispatcherServlet, httpServletRequest, httpServletResponse);
    }
}