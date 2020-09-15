package com.github.cccy0.my.spring.init;

import java.io.File;
import java.util.ArrayList;
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
            File file = new File(ClassScanner.class.getResource("../").toURI());
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
                String path = f.getPath().replace("\\", ".");
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
