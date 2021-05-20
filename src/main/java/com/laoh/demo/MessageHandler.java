package com.laoh.demo;

import com.laoh.core.WxConstants;
import com.laoh.core.annotation.*;
import com.laoh.core.entity.xml.XmlMessageRequest;
import com.laoh.core.entity.xml.XmlTextResponse;
import com.laoh.demo.dao.PlayerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 消息处理类示例
 * @author hyd
 * @date 2020/8/6 9:28
 */
@Slf4j
@MsgHandler
@Component
public class MessageHandler implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            MessageHandler.applicationContext = applicationContext;
        }
    }
    public static Object getBean(String beanName) {
        return MessageHandler.applicationContext.getBean(beanName);
    }
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
        StringBuilder stringBuilder = new StringBuilder();
        List<String> players = Arrays.asList(new String[]{"菲谢尔","迪奥娜","香菱","班尼特","莫娜","甘雨","胡桃","温迪",
        "钟离","行秋"});
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfWeek().getValue();
        if (now.getHour() < 4) {
            day = day == 1 ? 7 : day - 1;
        }
        List<String> todayPlayers = ((PlayerDao)getBean("playerDao")).prepareTalentMaterialForPlayer(day, players);
        if (todayPlayers.isEmpty()) {
            stringBuilder.append("今天没有需要培养的角色");
        } else {
            stringBuilder.append("今天需要培养的角色有：");
            for (String todayPlayer : todayPlayers) {
                stringBuilder.append(todayPlayer+"\n");
            }
        }
        response.setContent(stringBuilder.toString());
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
