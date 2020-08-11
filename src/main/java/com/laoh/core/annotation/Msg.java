package com.laoh.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 具体消息处理注解（如Text，Click）的元注解
 * @author hyd
 * @date 2020/8/6 13:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Msg {
    /**
     * 相应注解对应的字段值，如Click注解的微信数据包字段为CLICK
     * @return
     */
    String name();
}
