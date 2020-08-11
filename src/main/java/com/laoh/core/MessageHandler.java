package com.laoh.core;

import com.laoh.core.annotation.Click;
import com.laoh.core.annotation.MsgHandler;
import com.laoh.core.annotation.ResponseXml;
import com.laoh.core.annotation.Text;
import com.laoh.core.entity.xml.XmlMessageRequest;
import com.laoh.core.entity.xml.XmlTextResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息处理类
 * @author hyd
 * @date 2020/8/6 9:28
 */
@MsgHandler
@Slf4j
public class MessageHandler {
    /**
     * 执行文本消息的方法
     * @param message
     * @return
     */
    @Text(contains = "你好")
    @ResponseXml
    public Object textMessage(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("你好");
        return response;
    }
    /**
     * 执行文本消息的方法
     * @param message
     * @return
     */
    @Text(contains = "你")
    @ResponseXml
    public Object textMessage2(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("你");
        return response;
    }
    /**
     * 执行文本消息的方法
     * @param message
     * @return
     */
    @Text
    @ResponseXml
    public Object textMessage3(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("默认");
        return response;
    }
    /**
     * eventKey：菜单按钮key
     * @param message
     * @return
     */
    @Click(eventKey = "KEY_1")
    public Object clickEvent(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        log.info("KEY_1");
        return "success";
    }

    /**
     *
     * @param message
     * @return
     */
    @Click(eventKey = "KEY_2_1")
    @ResponseXml
    public Object clickEvent2(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("KEY_2_1");
        log.info("KEY_2_1");
        return response;
    }
}