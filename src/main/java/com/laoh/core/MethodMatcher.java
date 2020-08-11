package com.laoh.core;

import com.laoh.core.annotation.Match;
import com.laoh.core.entity.xml.XmlMessageRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 方法匹配器，针对不同的消息，找到最匹配的处理方法
 * @author hyd
 * @date 2020/8/7 19:22
 */
public class MethodMatcher {
    /**
     * @param methods
     * @param message
     * @param annotation
     * @return
     * @throws Exception
     */
    @Match(msgAction = WxConstants.XML_MSG_TEXT)
    public Method textMatcher(Set<Method> methods, XmlMessageRequest message, Class annotation) throws Exception {
        Method bestMatch = null;
        int matchLenth = 0;
        for (Method method : methods) {
            Annotation textAnnotation = method.getAnnotation(annotation);
            //获取Text注解上的contains字段的值
            String str = textAnnotation.annotationType().getMethod("contains").invoke(textAnnotation).toString();
            //如果contains为空，则立刻返回该方法
            if (str.equals("")) {
                return method;
            } else if (message.getContent().contains(str)) {
                if (str.length() > matchLenth) {
                    matchLenth = str.length();
                    bestMatch = method;
                }
            }
        }
        return bestMatch;
    }

    /**
     *
     * @param methods
     * @param message
     * @param annotation
     * @return
     * @throws Exception
     */
    @Match(msgAction = WxConstants.EVT_CLICK)
    public Method clickMatcher(Set<Method> methods, XmlMessageRequest message, Class annotation) throws Exception{
        for (Method method : methods) {
            Annotation clickAnnotation = method.getAnnotation(annotation);
            //获取Text注解上的eventKey字段的值
            String eventKey = clickAnnotation.annotationType().getMethod("eventKey").invoke(clickAnnotation).toString();
            if (message.getEventKey().equals(eventKey)) {
                return method;
            }
        }
        return null;
    }
}
