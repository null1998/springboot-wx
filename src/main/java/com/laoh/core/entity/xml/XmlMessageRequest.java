package com.laoh.core.entity.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author hyd
 * @date 2020/7/22 12:35
 */

/**
 * 消息请求
 */
@Getter@Setter@ToString
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlMessageRequest {
    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private Long CreateTime;
    /**
     * 消息类型
     */
    private String MsgType;
    /**
     * 文本消息内容
     */
    private String Content;
    /**
     * 事件类型
     */
    private String Event;
    /**
     * 事件KEY值
     */
    private String EventKey;
    /**
     * 二维码key值
     */
    private String Ticket;

    /**
     * 消息id，64位整型
     */
    private Long MsgId;
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;
    /**
     * 消息媒体id，可以调用获取临时素材接口拉取数据
     */
    private String MediaId;
    /**
     * 语音格式
     */
    private String Format;
    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;
    /**
     * 上报地理位置事件-纬度
     */
    private String Latitude;
    /**
     * 上报地理位置事件-经度
     */
    private String Longitude;
    /**
     * 上报地理位置事件-精度
     */
    private String Precision;

    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;
    private String ScanCodeInfo;
    private String ScanType;
    private String ScanResult;




}
