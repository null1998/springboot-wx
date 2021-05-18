package com.laoh.demo;

import com.laoh.core.WxConstants;
import com.laoh.core.annotation.*;
import com.laoh.core.entity.xml.XmlMessageRequest;
import com.laoh.core.entity.xml.XmlTextResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息处理类示例
 * @author hyd
 * @date 2020/8/6 9:28
 */
@Slf4j
@MsgHandler
public class MessageHandler {
    /**
     * 执行文本消息的方法
     * @param message
     * @return
     */
    @Text(contains = "原神")
    @ResponseXml
    public Object textMessage(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("你好啊");
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
        response.setContent("该消息关键字为你");
        return response;
    }
    @Image
    public Object imageMessage(XmlMessageRequest message) {
        return "success";
    }
    /**
     * eventKey：菜单按钮key
     * @param message
     * @return
     */
    @Click(eventKey = "key1_1")
    public Object clickEvent(XmlMessageRequest message) {
        return "success";
    }

    /**
     *
     * @param message
     * @return
     */
    @Click(eventKey = "key1_2")
    @ResponseXml
    public Object clickEvent2(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("来自按钮key1_2的点击事件");
        return response;
    }
    @Voice
    @ResponseXml
    public Object voice(XmlMessageRequest message) {
        XmlTextResponse response = new XmlTextResponse(message);
        response.setMsgType(WxConstants.XML_MSG_TEXT);
        response.setContent("语音消息内容"+message.getRecognition());
        return response;
    }

    @View(eventKey = "http://www.baidu.com/")
    @ResponseXml
    public Object viewEvent(XmlMessageRequest message) {
        return "success";
    }

    @View(eventKey = "https://www.bilibili.com/")
    @ResponseXml
    public Object viewEvent2(XmlMessageRequest message) {
        return "success";
    }
}
