package com.laoh.core;


import com.laoh.core.annotation.Msg;
import com.laoh.core.annotation.MsgHandler;
import com.laoh.core.utils.ClassScanner;
import com.laoh.core.utils.ScannerUtil;
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
        List<Class<?>> handlers = ScannerUtil.getClassesWithAnnotationFromPackage(packageName, MsgHandler.class);
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
        String packageName = "com.laoh.core.annotation";
        List<Class<?>> clazzList = ScannerUtil.getClassesWithAnnotationFromPackage(packageName, Msg.class);
        for (Class<?> clazz : clazzList) {
            if (clazz.isAnnotationPresent(Msg.class)) {
                map.put((String) clazz.getAnnotation(Msg.class).annotationType().getMethod("name").invoke(clazz.getAnnotation(Msg.class)), clazz);
                set.add(clazz);
            }
        }
        WxConfig.getInstance().setMsgActionMap(map);
        WxConfig.getInstance().setMsgActionSet(set);
    }
}
