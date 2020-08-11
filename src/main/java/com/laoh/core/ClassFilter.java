package com.laoh.core;

/**
 * 类过滤器
 * @author hyd
 * @date 2020/8/6 9:49
 */
public interface ClassFilter {
    boolean accept(Class<?> c);
}
