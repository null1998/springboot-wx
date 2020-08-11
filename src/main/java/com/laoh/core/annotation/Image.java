package com.laoh.core.annotation;

import com.laoh.core.WxConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理图片事件
 * @author hyd
 * @date 2020/8/6 9:20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Msg(name = WxConstants.XML_MSG_IMAGE)
public @interface Image {
}
