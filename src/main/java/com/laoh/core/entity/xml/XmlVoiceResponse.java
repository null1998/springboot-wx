package com.laoh.core.entity.xml;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 语音响应
 * @author hyd
 * @date 2020/8/4 11:10
 */
@Setter
@NoArgsConstructor
@XmlRootElement(name = "xml")
public class XmlVoiceResponse extends XmlResponse{
    @XmlElement(name = "Voice")
    private Voice voice;
    @Setter
    public static class Voice {
        @XmlElement(name = "MediaId")
        private String mediaId;
    }
    public XmlVoiceResponse(XmlMessageRequest message) {
        super(message);
    }
}
