package com.laoh.core.entity.xml;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 图片响应
 * @author hyd
 * @date 2020/8/4 11:00
 */
@Setter
@NoArgsConstructor
@XmlRootElement(name = "xml")
public class XmlImageResponse extends XmlResponse {
    @XmlElement(name = "Image")
    private Image image;
    @Setter
    public static class Image {
        @XmlElement(name = "MediaId")
        private String mediaId;
    }
    public XmlImageResponse(XmlMessageRequest message) {
        super(message);
    }

}
