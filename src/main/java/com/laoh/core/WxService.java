package com.laoh.core;

import com.laoh.core.annotation.Match;
import com.laoh.core.annotation.ResponseXml;
import com.laoh.core.dao.WxMessageStatusDao;
import com.laoh.core.entity.WxMessageStatus;
import com.laoh.core.entity.json.JsonButtonEntity;
import com.laoh.core.entity.xml.XmlMessageRequest;
import com.laoh.core.entity.xml.XmlResponse;
import com.laoh.core.exception.WxException;
import com.laoh.utils.HttpClientUtil;
import com.laoh.utils.JaxbUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author hyd
 * @date 2020/7/23 19:40
 */
@Slf4j
@Component
public class WxService implements IService{
    @Autowired
    WxMessageStatusDao wxMessageStatusDao;
    /**
     * 将button以json的格式加入到post请求体中
     * @param buttons 一级菜单，最多三个
     * @return 创建菜单是否成功
     */
    @Override
    public boolean menuCreate(List<JsonButtonEntity> buttons) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_CREATE_MENU.replace("ACCESS_TOKEN", accessToken);
        Map<String, Object> entityMap = new HashMap<>();
        entityMap.put("button",buttons);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doPost(url,null,entityMap);
        log.info("创建自定义菜单："+result.getContent());
        JSONObject jsonObject = new JSONObject(result.getContent());
        return Integer.parseInt(jsonObject.get("errcode").toString()) == 0;
    }

    /**
     * @return 自定义菜单的配置，json格式
     */
    @Override
    public String menuQuery() {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_GET_CURRENT_MENU_INFO.replace("ACCESS_TOKEN", accessToken);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doGet(url);
        log.info("查询当前菜单配置："+result.getContent());
        return result.getContent();
    }

    /**
     * 删除菜单
     * @return
     */
    @Override
    public boolean menuDelete() {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_DELETE_MENU.replace("ACCESS_TOKEN", accessToken);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doGet(url);
        log.info("删除菜单");
        JSONObject jsonObject = new JSONObject(result.getContent());
        return Integer.parseInt(jsonObject.get("errcode").toString()) == 0;
    }

    /**
     * 针对不同的消息，找到最匹配的方法，
     * 拿到返回结果，转换为xml格式或直接返回普通字符串
     * @param message
     * @return
     */
    @SneakyThrows
    @Override
    public String receiveMessage(XmlMessageRequest message) {
        //消息的类型，如text，CLICK
        String msgAction;
        //消息的标识，普通消息使用msgId，事件使用FromUserName+CreateTime
        String msgKey;
        if (message.getMsgType().equals(WxConstants.XML_MSG_EVENT)) {
            msgAction = message.getEvent();
            msgKey = message.getFromUserName() + message.getCreateTime();
        } else {
            msgAction = message.getMsgType();
            msgKey = String.valueOf(message.getMsgId());
        }
        WxMessageStatus wxMessageStatus = wxMessageStatusDao.selectWxMessageStatus(msgKey);
        if (wxMessageStatus == null) {
            wxMessageStatusDao.insertWxMessageStatus(msgKey, "新建", null, 1);
        } else if (wxMessageStatus.getRequestCount() == 1) {
            if (wxMessageStatus.getResult() != null) {
                return JaxbUtil.toXml(wxMessageStatus.getResult());
            } else {
                while (true) {

                }
            }
        } else {

        }
        WxConfig wxConfig = WxConfig.getInstance();
        //利用msgAction找出对应的注解，例如msgAction为CLICK,则对应注解为Click.class
        Class annotation= wxConfig.getMsgActionMap().get(msgAction);
        //将所有包含特定注解的方法从map中取出来
        HashSet<Method> methods = wxConfig.getAnnotationMethodsMap().get(annotation);
        Method matchMethod = bestMatch(methods, message, msgAction, annotation);
        if (matchMethod == null) {
            throw new WxException("没有处理消息"+message+"的方法");
        }
        Object response = matchMethod.invoke(matchMethod.getDeclaringClass().newInstance(), message);
        if (matchMethod.isAnnotationPresent(ResponseXml.class)) {
            if (response instanceof XmlResponse) {
                return JaxbUtil.toXml(response);
            } else {
                throw new WxException("不是XmlResponse子类，无法转化为xml");
            }
        }
        return response.toString();

    }

    /**
     *私有方法，用于帮助不同消息找到匹配方法
     * @param methods 消息处理注解对应方法集合
     * @param message 消息
     * @param msgAction 消息类型，例如text，CLICK
     * @param annotation 消息处理注解，例如Text.class,Click.class
     * @return 执行方法
     */
    private Method bestMatch(Set<Method> methods, XmlMessageRequest message, String msgAction, Class annotation) throws Exception {
        Method bestMatch = null;
        Class c = new MethodMatcher().getClass();
        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Match.class)) {
                //取出MethodMatcher方法上Match注解的值
                String str = (String) m.getAnnotation(Match.class).annotationType().getMethod("msgAction").invoke(m.getAnnotation(Match.class));
                if (str.equals(msgAction)) {
                    bestMatch = (Method) m.invoke(c.newInstance(), methods, message, annotation);
                }
            }

        }
        return bestMatch;
    }

    /**
     * 将临时素材用post form-data/multipart方式上传
     * @param type 媒体文件类型
     * @param path 媒体文件路径
     */
    @Override
    public void uploadTempMedia(String type, String path) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_UPLOAD_TEMP_MEDIA.
                replace("ACCESS_TOKEN", accessToken)
                .replace("TYPE",type);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doPostMultipart(url, path);
        System.out.println(result);
    }

}
