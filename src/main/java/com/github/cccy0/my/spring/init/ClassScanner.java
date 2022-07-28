package com.github.cccy0.my.spring.init;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用来扫描当前项目所有Class
 * @author Zhai
 * 2020/9/15 17:10
 */

public class ClassScanner {
    public static List<Class<?>> classes = new ArrayList<>();

    public static void doScan() {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("");
            if (url == null) {
                throw new Exception("classpath error !");
            }
            System.out.println("current base path: " + url.toString());
            // todo: 这边包扫描的实现思路错误了, 等有空再改
            String[] urlStrings = url.toString().split("/");
            StringBuilder urlString = new StringBuilder();
            for (int i = 0; i < urlStrings.length - 1; i++) {
                urlString.append(urlStrings[i]).append("/");
            }
            System.out.println(urlString);
            File file = new File(new URI(urlString.toString()));
            doScan(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doScan(File file) {
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                doScan(f);
            } else {
                // 将路径中的\转换为.
                // pathExample:  D:\acy\project\personal\my-spring\target\classes\com\github\cccy0\my\spring
                String path = f.getPath().replace("\\", ".").replace("/", ".");
                if (!path.endsWith(".class")) {
                    continue;
                }
                String className = path.substring(path.lastIndexOf("classes.") + 8, path.lastIndexOf(".class"));
                try {
                    classes.add(Class.forName(className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
