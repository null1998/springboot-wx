package com.laoh.core.entity.xml;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * xml响应数据包的基础字段
 * @author hyd
 * @date 2020/8/4 8:49
 */
@Setter
@NoArgsConstructor
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class XmlResponse implements Serializable {
    private static final long serialVersionUID = -8740211874336546160L;
    @XmlElement(name = "ToUserName")
    protected String toUserName;
    @XmlElement(name = "FromUserName")
    protected String fromUserName;
    @XmlElement(name = "CreateTime")
    protected String createTime;
    @XmlElement(name = "MsgType")
    protected String msgType;

    public XmlResponse(XmlMessageRequest message) {
        setFromUserName(message.getToUserName());
        setToUserName(message.getFromUserName());
        setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
    }

}
