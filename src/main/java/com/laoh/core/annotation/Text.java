package com.laoh.core.annotation;

import com.laoh.core.WxConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理text消息
 * @author hyd
 * @date 2020/8/6 9:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Msg(name = WxConstants.XML_MSG_TEXT)
public @interface Text {
    /**
     * 可以设置文本消息关键字，含有对应关键字的文本消息会被发送到对应的方法中进行处理
     * 比如，用户发送“三国杀卡牌”，如果有一个方法有@Text(contains = "三国杀")，则该方法
     * 处理此消息。如果有多个方法都可以匹配，例如还有一个方法有@Text(contains = "三国")，
     * 则匹配contains值长的方法。注意，该注解contains默认为空，当存在contains为空的值时，
     * 任何文本消息都会发送到该方法，其它方法失效。
     * @return
     */
    String contains() default "";
}
