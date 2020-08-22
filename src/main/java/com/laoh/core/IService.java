package com.laoh.core;

import com.laoh.core.entity.json.JsonButtonEntity;
import com.laoh.core.entity.xml.XmlMessageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

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
    public boolean menuCreate(List<JsonButtonEntity> buttons);
    /**
     * 查询自定义菜单的配置
     * @return 自定义菜单的配置，json格式
     */
    public String menuQuery();

    /**
     * 删除自定义菜单
     * @return 删除菜单是否成功
     */
    public boolean menuDelete();

    /**
     * 接收微信消息和事件推送并返回结果
     * @param message
     * @return
     */
    public String receiveMessage(XmlMessageRequest message);
    /**
     * 新增临时素材
     * @param type 媒体文件类型
     * @param path 媒体文件路径
     * @return
     */
    public String uploadTempMaterial(WxMediaType type, String path);

    /**
     * 下载临时图片
     * @param media_id
     * @return
     */
    public ResponseEntity<byte[]> downloadTempImage(String media_id);

    /**
     * 下载临时视频
     * @param media_id
     * @return
     */
    public String downloadTempVideo(String media_id);

    /**
     * 下载临时语音
     * @param media_id
     * @return
     */
    public ResponseEntity<byte[]> downloadTempVoice(String media_id);

    /**
     * 新增永久素材(含视频标题和介绍)
     * @param type
     * @param path
     * @param title
     * @param introduction
     * @return json字符串
     */
    public String uploadMaterial(WxMediaType type, String path, String title, String introduction);
    /**
     * 获取永久素材
     * @param media_id
     */
    ResponseEntity<byte[]> downloadMaterial(String media_id);

    /**
     * 删除永久素材
     * @param media_id
     * @return
     */
    boolean deleteMaterial(String media_id);

    /**
     * 获取永久素材的总数
     * @return
     */
    Map<String, Integer> getMaterialCount();

    /**
     *获取永久素材的列表
     */
    void  batchGetMaterial();

}
