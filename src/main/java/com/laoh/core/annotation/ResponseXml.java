package com.laoh.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将返回值（要求返回值是XmlResponse子类对象）转化为XML字符串，方便返回给微信服务器，不加则返回给微信服务器普通字符串
 * @author hyd
 * @date 2020/8/7 19:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResponseXml {
}
