package com.github.cccy0.my.spring.web;

import com.github.cccy0.my.spring.factory.BeanFactory;
import com.github.cccy0.my.spring.factory.URLFactory;
import com.github.cccy0.my.spring.ioc.BeanInfo;
import com.github.cccy0.my.spring.ioc.UrlMethodRelate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhai
 * 2020/9/18 17:18
 */

public class MethodInvoke {
    public static String invoke(HttpServletRequest req, HttpServletResponse res) {
        try (PrintWriter writer = res.getWriter()) {
            // 获取uri
            String uri = req.getRequestURI();
            if (uri.lastIndexOf("?") != -1) {
                uri = uri.substring(0, uri.lastIndexOf("?"));
            }
            // 获取对应的method
            UrlMethodRelate urlMethodRelate = URLFactory.getInstance().gainUrlMethodRelate(uri);
            if (urlMethodRelate == null) {
                return "404";
            }
            // 获取bean
            BeanInfo bi = BeanFactory.getInstance().gainBean(urlMethodRelate.getClassName());
            Object bean = bi.gainInvokeObj();
            // 获取方法
            BeanInfo.MethodInfo mi = bi.gainMethod(urlMethodRelate.getMethodName());
            Method method = mi.getMethod();
            // 方法的参数列表(名称) (是有序的, 与方法参数列表顺序一致)
            List<String> paramNames = mi.getParamNames();
            // 构建入参Map
            List<Object> params = new ArrayList<>(paramNames.size());
            // 匹配入参 这儿要保持方法参数的顺序, 所以根据参数名称来取request里面的参数
            for (String paramName : paramNames) {
                params.add(req.getParameter(paramName));
            }
            // invoke
            Object result = method.invoke(bean, params.toArray());
            if (result != null) {
                writer.write(result.toString());
            }
            return "200";
        } catch (Exception e) {
            return "500";
        }
    }
}
