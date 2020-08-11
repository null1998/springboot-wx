package com.laoh.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于匹配者类，用于区分不同消息类型
 * @author hyd
 * @date 2020/8/7 19:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Match {
    /**
     * 消息类型，如text，CLICK
     * @return
     */
    String msgAction();
}
