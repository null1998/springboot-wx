package com.laoh.core.annotation;

import com.laoh.core.WxConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hyd
 * @date 2020/8/15 12:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Msg(name = WxConstants.XML_MSG_LINK)
public @interface Link {
}
