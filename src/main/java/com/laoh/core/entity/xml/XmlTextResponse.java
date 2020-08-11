package com.laoh.core.entity.xml;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 文本响应
 * @author hyd
 * @date 2020/8/4 10:57
 */
@Setter
@NoArgsConstructor
@XmlRootElement(name = "xml")
public class XmlTextResponse extends XmlResponse {
    @XmlElement(name = "Content")
    private String content;
    public XmlTextResponse(XmlMessageRequest message) {
        super(message);
    }
}
