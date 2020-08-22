package com.laoh.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 暂时不用
 * @author hyd
 * @date 2020/8/9 10:41
 */
@ToString
@Getter
@Setter
public class WxMessageStatus{
    private Integer requestCount;
    private String status;
    private String result;

    public WxMessageStatus(Integer requestCount, String status, String result) {
        this.requestCount = requestCount;
        this.status = status;
        this.result = result;
    }
}
