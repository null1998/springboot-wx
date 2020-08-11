package com.laoh.core.entity.json;

import lombok.Data;

import java.util.List;

/**一级菜单数组，1-3个
 * JsonButtonEntity
 * @author hyd
 * @date 2020/7/19 16:17
 */
@Data
public class JsonButtonEntity {
    /**
     * 菜单响应动作类型，必须是微信文档指定的，例如click：点击，view：网页，miniprogram：小程序
     */
    private String type;
    /**
     * 菜单标题（不大于16字节），子菜单不超过60字节
     */
    private String name;
    /**
     * 菜单key值，用于消息推送，点击类型必须
     */
    private String key;
    /**
     * view，miniprogram必须
     */
    private String url;
    /**
     * 调用永久新增素材接口返回的合法media_id
     * media,view_limited必须
     */
    private String media_id;
    /**
     * 小程序appid(仅认证公众号)
     */
    private String appid;
    /**
     * 小程序页面路径
     */
    private String pagepath;
    /**
     * 二级菜单数组，1-5个
     */
    private List<JsonButtonEntity> sub_button;
}
