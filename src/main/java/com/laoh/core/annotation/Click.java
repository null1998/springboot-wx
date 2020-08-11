package com.laoh.core.annotation;

import com.laoh.core.WxConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理click事件
 * @author hyd
 * @date 2020/8/6 9:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Msg(name = WxConstants.EVT_CLICK)
public @interface Click {
    /**
     * 根据菜单按钮key值处理不同click事件
     * @return
     */
    String eventKey();
}
