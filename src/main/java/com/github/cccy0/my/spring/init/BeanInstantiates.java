package com.github.cccy0.my.spring.init;

import com.github.cccy0.my.spring.annotation.CYAopBean;
import com.github.cccy0.my.spring.annotation.CYController;
import com.github.cccy0.my.spring.annotation.CYService;
import com.github.cccy0.my.spring.factory.BeanFactory;
import com.github.cccy0.my.spring.ioc.BeanInfo;
import com.github.cccy0.my.spring.ioc.BeanInfo.MethodInfo;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 实例化所有Bean
 *
 * @author Zhai
 * 2020/9/15 18:04
 */

public class BeanInstantiates {

    public static void init(List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            boolean flag = false;
            BeanInfo bi = new BeanInfo();
            if (clazz.isAnnotationPresent(CYController.class)) {
                flag = true;
                CYController controller = clazz.getAnnotation(CYController.class);
                String beanName = controller.value();
                if ("".equals(beanName)) {
                    beanName = clazz.getSimpleName();
                    beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                }
                addBeanProperties(bi, beanName, clazz.getName(), BeanInfo.CONTROLLER);
            } else if (clazz.isAnnotationPresent(CYService.class)) {
                flag = true;
                CYService service = clazz.getAnnotation(CYService.class);
                String beanName = service.value();
                if ("".equals(beanName)) {
                    beanName = clazz.getSimpleName();
                    beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                }
                addBeanProperties(bi, beanName, clazz.getName(), BeanInfo.SERVICE);
            } else if (clazz.isAnnotationPresent(CYAopBean.class)) {
                flag = true;
                CYAopBean aopBean = clazz.getAnnotation(CYAopBean.class);
                String beanName = aopBean.value();
                if ("".equals(beanName)) {
                    beanName = clazz.getSimpleName();
                    beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                }
                addBeanProperties(bi, beanName, clazz.getName(), BeanInfo.AOP);
            }
            if (!flag) {
                continue;
            }
            try {
                // 反射生成Bean
                bi.setBean(clazz.newInstance());
                // 获取所有方法
                Method[] methods = clazz.getDeclaredMethods();
                // 存储方法信息
                List<MethodInfo> methodInfos = new ArrayList<>(methods.length);
                for (Method m : methods) {
                    // 去除静态方法 (todo: 这边实现思路也不太对, 以后再研究)
                    if (Modifier.isStatic(m.getModifiers())) {
                        continue;
                    }
                    MethodInfo mi = new MethodInfo();
                    // 方法名
                    mi.setMethodName(m.getName());
                    // 入参名称
                    mi.setParamNames(getParamNames(m.getName(), clazz));
                    // 入参类型
                    mi.setParamTypes(Arrays.asList(m.getParameterTypes()));
                    // 方法对象
                    mi.setMethod(m);
                    methodInfos.add(mi);
                }
                // 添加 methodInfos 到 Bean
                bi.setMethods(methodInfos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 放入工厂
            BeanFactory.getInstance().addBean(bi);
        }
    }

    private static void addBeanProperties(BeanInfo bi, String name, String className, String type) {
        bi.setName(name);
        bi.setClassName(className);
        bi.setBeanType(type);
    }

    /**
     * 通过方法名和类对象，获取该方法的入参名称列表
     *
     * @param methodName 方法名称
     * @param clazz      类对象
     * @return 入参名称列表
     */
    private static List<String> getParamNames(String methodName, Class<?> clazz) {
        try {
            List<String> paramNames = new ArrayList<>();
            ClassPool pool = ClassPool.getDefault();
            // 手动将类路径加入pool
            URL resource = Thread.currentThread().getContextClassLoader().getResource("");
            if (resource == null) {
                throw new Exception("path error !");
            }
            pool.insertClassPath(resource.toURI().getPath());
            CtClass ctClass = pool.getCtClass(clazz.getName());
            CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
            // 使用javassist的反射方法的参数名
            javassist.bytecode.MethodInfo methodInfo = ctMethod.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            if (attr != null) {
                int len = ctMethod.getParameterTypes().length;
                // 非静态的成员函数的第一个参数是this
                int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
                for (int i = 0; i < len; i++) {
                    paramNames.add(attr.variableName(i + pos));
                }

            }
            return paramNames;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
