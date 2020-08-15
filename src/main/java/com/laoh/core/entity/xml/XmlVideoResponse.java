package com.laoh.core.entity.xml;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hyd
 * @date 2020/8/15 6:13
 */
@Setter
@NoArgsConstructor
@XmlRootElement(name = "xml")
public class XmlVideoResponse extends XmlResponse{
    @XmlElement(name = "Video")
    private Video video;
    @Setter
    public static class Video {
        @XmlElement(name = "MediaId")
        private String mediaId;
        @XmlElement(name = "Title")
        private String title;
        @XmlElement(name = "Description")
        private String description;
    }
    public XmlVideoResponse(XmlMessageRequest message) {
        super(message);
    }
}
