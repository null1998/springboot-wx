package com.laoh.core.entity.xml;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * 图文响应
 * @author hyd
 * @date 2020/8/4 8:49
 */
@Setter
@NoArgsConstructor
@XmlRootElement(name = "xml")
public class XmlNewsResponse extends XmlResponse {
    @XmlElement(name = "ArticleCount")
    private String articleCount;
    @XmlElementWrapper(name = "Articles")
    @XmlElement(name = "item")
    private ArrayList<Article> articleList;

    @Setter
    public static class Article {
        @XmlElement(name = "Title")
        private String title;
        @XmlElement(name = "Description")
        private String description;
        @XmlElement(name = "PicUrl")
        private String picUrl;
        @XmlElement(name = "Url")
        private String url;
    }
    public XmlNewsResponse(XmlMessageRequest message) {
        super(message);
    }
}
