package com.laoh.core.entity;

import com.laoh.core.entity.xml.XmlResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hyd
 * @date 2020/8/9 10:41
 */
@ToString
@Getter
@Setter
public class WxMessageStatus{
    private Integer id;
    private String mkey;
    private String status;
    private XmlResponse result;
    private Integer requestCount;
}
