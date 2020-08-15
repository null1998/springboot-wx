package com.laoh.core.entity.xml;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hyd
 * @date 2020/8/15 6:16
 */
@Setter
@NoArgsConstructor
@XmlRootElement(name = "xml")
public class XmlMusicResponse extends XmlResponse{
    @XmlElement(name = "Music")
    private Music music;
    @Setter
    static class Music {
        @XmlElement(name = "Title")
        private String title;
        @XmlElement(name = "Description")
        private String description;
        @XmlElement(name = "MusicUrl")
        private String musicUrl;
        @XmlElement(name = "HQMusicUrl")
        private String hqMusicUrl;
        @XmlElement(name = "ThumbMediaId")
        private String thumbMediaId;

    }
    public XmlMusicResponse(XmlMessageRequest message) {
        super(message);
    }
}
