package com.laoh.core.utils;


import com.laoh.core.ClassFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * class文件扫描器
 *
 * @author yanduohuang
 * @date 2018/9/9
 */
public class ClassScanner {
    /**
     * 文件筛选器，自定义筛选逻辑
     */
    private static final FileFilter CLASS_FILE_FILTER = file -> file.isFile() && file.getName().endsWith(".class");

    private static final FileFilter DIRECTORY_FILTER = File::isDirectory;
    //class文件夹在计算机上的路径
    private String classesPath;
    //类过滤器
    private Collection<ClassFilter> classFilters;

    public ClassScanner(String classesPath) {
        this.classesPath = classesPath;
        this.classFilters = new LinkedList<>();
    }

    public void addFilter(ClassFilter classFilter) {
        if (classFilter != null) {
            this.classFilters.add(classFilter);
        }
    }

    /**
     * 将要对packageName下的所有类进行扫描
     *
     * @param packageName 包名
     * @return 通过了筛选的类的集合
     */
    public Collection<Class<?>> scan(String packageName) {

        File dir = new File(this.classesPath, packageName.replace('.', '/'));

        Collection<String> classeNames = listAllClassNames(dir, packageName);

        Collection<Class<?>> classes = new LinkedList<>();
        for (String className : classeNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (this.accept(clazz)) {

                    classes.add(clazz);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return classes;
    }

    private boolean accept(Class<?> clazz) {

        for (ClassFilter classFilter : this.classFilters) {
            if (!classFilter.accept(clazz)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param dir class目录
     * @param packageName 包名
     * @return 类名集合
     */
    private Collection<String> listAllClassNames(File dir, String packageName) {
        File[] classFiles = dir.listFiles(CLASS_FILE_FILTER);
        File[] directories = dir.listFiles(DIRECTORY_FILTER);

        List<String> allClassNames = new LinkedList<>();
        if (classFiles != null && classFiles.length > 0) {
            for (File classFile : classFiles) {
                String simpleClassName = classFile.getName().replace(".class", "");
                allClassNames.add((packageName + '.' + simpleClassName));
            }
        }
        //如果发现子文件夹，进行递归操作找出子文件夹下的所有类的名字
        if (directories != null && directories.length > 0) {
            for (File subdir : directories) {
                allClassNames.addAll(listAllClassNames(subdir, packageName + '.' + subdir.getName()));
            }
        }
        return allClassNames;
    }
}
