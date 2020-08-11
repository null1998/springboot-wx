package com.laoh.core.exception;

/**
 * 微信公众号框架中的一些异常
 * @author hyd
 * @date 2020/7/24 22:28
 */
public class WxException extends Exception{
    public WxException(String msg) {
        super(msg);
    }
}
