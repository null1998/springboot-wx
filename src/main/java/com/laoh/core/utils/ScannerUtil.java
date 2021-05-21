package com.laoh.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描工具类，动态获取类和方法与属性
 *
 * @author yanduohuang
 * @date 2021/5/21 18:10
 */
@Slf4j
public class ScannerUtil {
    /**
     * 根据包名和类注解扫描类
     *
     * @param packageName 包名
     * @param annotation 类注解
     * @return 类对象列表
     */
    public static List<Class<?>> getClassesWithAnnotationFromPackage(String packageName, Class<? extends Annotation> annotation) {
        List<Class<?>> classList = new ArrayList<>();
        Enumeration<URL> dirs = getUrlFromPackageName(packageName);
        if (dirs == null) {
            return new ArrayList<>();
        }
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();

            if ("file".equals(protocol) ) {
                // 普通文件夹形式
                String filePath = getFilePathFromUrl(url);
                if (filePath != null && filePath.startsWith("/")) {
                    // 去除斜杠
                    filePath = filePath.substring(1);
                    getClassesWithAnnotationFromFilePath(packageName, filePath, classList, annotation);
                }
            } else if ("jar".equals(protocol)) {
                // jar包形式
                JarFile jar = getJarFileFromUrl(url);
                if (jar != null) {
                    // 储存jar包下的
                    List<Class<?>> alClassList = new ArrayList<>();
                    findClassesByJar(packageName, jar, alClassList);
                    getClassesWithAnnotationFromAllClasses(alClassList, annotation, classList);
                }
            }
            else {
                log.warn("can't process the protocol={}", protocol);
            }
        }

        return classList;
    }

    /**
     * 从包名中获取url对象列表
     *
     * @param packageName 包名
     * @return 对象列表
     */
    private static Enumeration<URL> getUrlFromPackageName(String packageName) {
        String packageDirName = packageName.replace('.', '/');
        try {
            // 读取指定包名下的文件
            return Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        }
        catch (IOException e) {
            log.error("Failed to get resource", e);
        }
        return null;
    }


    /**
     * 从url中获取文件路径
     *
     * @param url url
     * @return 文件路径
     */
    private static String getFilePathFromUrl(URL url) {
        try {
            return URLDecoder.decode(url.getFile(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            log.error("Failed to decode class file", e);
        }
        return url.getFile();
    }

    /**
     * 从url中获取JarFile对象
     *
     * @param url url
     * @return JarFile对象
     */
    private static JarFile getJarFileFromUrl(URL url) {
        try {
            return ((JarURLConnection) url.openConnection()).getJarFile();
        }
        catch (Exception e) {
            log.error("Failed to decode class jar", e);
        }
        return null;
    }


    /**
     * 从jar文件下获取所有类对象
     *
     * @param pkgName 包名
     * @param jar jar文件
     * @param classes 类对象列表
     */
    private static void findClassesByJar(String pkgName, JarFile jar, List<Class<?>> classes) {
        String pkgDir = pkgName.replace(".", "/");
        Enumeration<JarEntry> entry = jar.entries();

        while (entry.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录和一些jar包里的其他文件 如META-INF等文件
            JarEntry jarEntry = entry.nextElement();
            // 路径形式
            String name = jarEntry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }
            // 不符合条件的跳过
            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                continue;
            }
            // 去掉后面的".class"
            String classPath = name.substring(0, name.length() - 6);
            // 路径形式转化为完整类名形式，并获取类对象
            Class<?> tempClass = loadClass(classPath.replace("/", "."));
            // 添加到集合中去
            if (tempClass != null) {
                classes.add(tempClass);
            }
        }
    }

    /**
     * 根据全限定名加载类对象
     * @param fullClassName 类全名
     * @return 类对象
     */
    private static Class<?> loadClass(String fullClassName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClassName);
        } catch (ClassNotFoundException e) {
            log.error("fullClassName loadClass", e);
        }
        return null;
    }

    /**
     * 从指定文件路径下获取含有指定类注解的类对象
     *
     * @param packageName 包名
     * @param filePath 文件路径
     * @param classList 类对象列表
     * @param annotation 类注解
     */
    private static void getClassesWithAnnotationFromFilePath(String packageName, String filePath, List<Class<?>> classList,
                                                      Class<? extends Annotation> annotation) {
        Path dir = Paths.get(filePath);

        try {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path path : stream) {
                    String fileName = String.valueOf(path.getFileName());
                    // 如果是目录，需要进行递归
                    boolean isDir = Files.isDirectory(path);
                    if (isDir) {
                        getClassesWithAnnotationFromFilePath(packageName + "." + fileName, path.toString(), classList, annotation);
                    } else {
                        // 去除.class
                        String className = fileName.substring(0, fileName.length() - 6);
                        String fullClassName = packageName + "." + className;
                        Class<?> clazz = loadClass(fullClassName);
                        // 含有指定类注解
                        if (clazz != null && clazz.getAnnotation(annotation) != null) {
                            classList.add(clazz);
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            log.error("Failed to read class file", e);
        }
    }

    /**
     * 从输入的类对象集合中筛选含有指定类注解的类对象
     *
     * @param inClassList 输入类对象集合
     * @param annotation 指定类注解
     * @param outClassList 输出类对象集合
     */
    private static void getClassesWithAnnotationFromAllClasses(List<Class<?>> inClassList,
                                                        Class<? extends Annotation> annotation, List<Class<?>> outClassList) {
        for(Class<?> clazz : inClassList) {
            if (clazz != null && clazz.getAnnotation(annotation) != null) {
                outClassList.add(clazz);
            }
        }
    }

    /**
     * 从指定类对象中获取含有指定方法注解的方法
     *
     * @param clazz 类对象
     * @param methodList 方法对象列表
     * @param methodAnnotation 方法注解
     */
    private static void geMethodWithAnnotationFromFullClassName(Class clazz, List<Method> methodList,
                                                    Class<? extends Annotation> methodAnnotation) {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method != null && method.getAnnotation(methodAnnotation) != null) {
                methodList.add(method);
            }
        }
    }
    private ScannerUtil(){}




}
