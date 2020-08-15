package com.laoh.core.annotation;

import com.laoh.core.WxConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hyd
 * @date 2020/8/15 6:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Msg(name = WxConstants.EVT_VIEW)
public @interface View {
    /**
     * 根据跳转url的值处理不同view事件
     * @return
     */
    String eventKey();
}
