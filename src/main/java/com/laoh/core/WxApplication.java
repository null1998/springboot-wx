package com.laoh.core;

import com.laoh.Main;
import com.laoh.core.annotation.Msg;
import com.laoh.core.annotation.MsgHandler;
import com.laoh.utils.ClassScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 *进行微信框架的一些初始化工作
 * @author hyd
 * @date 2020/8/6 9:51
 */
@Component
@Slf4j
public class WxApplication implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        scanAnnotation();
        scanHandler();
    }

    /**
     * 扫描消息处理类
     * @throws URISyntaxException
     */
    public void scanHandler() throws URISyntaxException {
        WxConfig wxConfig = WxConfig.getInstance();
        String packageName = wxConfig.getPackageName();
        URL url = Main.class.getClassLoader().getResource("");
        String classPath = url.toURI().getPath();
        ClassScanner classScanner = new ClassScanner(classPath);
        classScanner.addFilter(new ClassFilter() {
            @Override
            public boolean accept(Class<?> c) {
                return c.getAnnotation(MsgHandler.class) != null;
            }
        });
        Collection<Class<?>> handlers = classScanner.scan(packageName);
        Map<Class<?>, HashSet<Method>> annotMethodMap = new HashMap<>();
        for (Class<?> handler : handlers) {
            Method[] methods = handler.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (WxConfig.getInstance().getMsgActionSet().contains(annotation.annotationType())) {
                        HashSet<Method> set = annotMethodMap.getOrDefault(annotation.annotationType(), new HashSet<>());
                        set.add(method);
                        annotMethodMap.put(annotation.annotationType(), set);
                    }
                }
            }
        }
        wxConfig.setHandlers(handlers);
        wxConfig.setAnnotationMethodsMap(annotMethodMap);
    }

    /**
     * 扫描消息注解
     */
    public void scanAnnotation() throws Exception {
        Map<String, Class<?>> map = new HashMap<>();
        Set<Class<?>> set = new HashSet<>();
        URL url = Main.class.getClassLoader().getResource("");
        String classPath = url.toURI().getPath();
        String packageName = "com.laoh.core.annotation";
        File file = new File(classPath+packageName.replace(".","/"));
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".class");
            }
        });
        for (File f : files) {
            Class clazz = Class.forName(packageName+"."+f.getName().replace(".class",""));
            if (clazz.isAnnotationPresent(Msg.class)) {
                map.put((String) clazz.getAnnotation(Msg.class).annotationType().getMethod("name").invoke(clazz.getAnnotation(Msg.class)), clazz);
                set.add(clazz);
            }
        }
        WxConfig.getInstance().setMsgActionMap(map);
        WxConfig.getInstance().setMsgActionSet(set);
    }
}
