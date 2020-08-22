package com.laoh.core;

/**
 * @author hyd
 * @date 2020/8/21 17:12
 */
public enum WxMediaType {
    /**
     * 素材类型
     */
    IMAGE("image"),VOICE("voice"),VIDEO("video"),THUMB("thumb");
    private String name;
    private WxMediaType(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

}
