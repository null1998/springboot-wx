package com.laoh.core;

import com.laoh.core.entity.json.JsonButtonEntity;
import com.laoh.core.entity.xml.XmlMessageRequest;

import java.util.List;

/**
 * 微信公众号开发服务
 * @author hyd
 * @date 2020/7/23 19:38
 */
public interface IService {
    /**
     * 接入认证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echo
     * @return
     */
    public String checkSignature(String signature, String timestamp, String nonce, String echo);
    /**
     * 获取微信callback IP地址
     * @return
     */
    public List<Object> getCallBackIp();

    /**
     * 获取微信API接口 IP地址
     * @return
     */
    public List<Object> getApiDomainIp();
    /**
     * 创建自定义菜单
     * @param buttons 一级菜单，最多三个
     * @return 创建菜单是否成功
     */
    boolean menuCreate(List<JsonButtonEntity> buttons);
    /**
     * 查询自定义菜单的配置
     * @return 自定义菜单的配置，json格式
     */
    String menuQuery();

    /**
     * 删除自定义菜单
     * @return 删除菜单是否成功
     */
    boolean menuDelete();

    /**
     * 接收微信消息和事件推送并返回结果
     * @param message
     * @return
     */
    String receiveMessage(XmlMessageRequest message);
    /**
     * 新增临时素材
     * @param type 媒体文件类型
     * @param path 媒体文件路径
     */
    void uploadTempMedia(String type, String path);


}
