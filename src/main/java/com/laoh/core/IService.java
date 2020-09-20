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
     * @author yanduohuang
     * @description //TODO
     * @date 19:22 2020/9/16
     *
     * @param: signature
     * @param: timestamp
     * @param: nonce
     * @param: echo
     * @return: java.lang.String
     * @throws
     **/
    public String checkSignature(String signature, String timestamp, String nonce, String echo);
    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:22 2020/9/16
     *
     * @return: java.util.List<java.lang.Object>
     * @throws
     **/
    public List<Object> getCallBackIp();

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:22 2020/9/16
     *
     * @return: java.util.List<java.lang.Object>
     * @throws
     **/
    public List<Object> getApiDomainIp();
    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:22 2020/9/16
     *
     * @param: buttons
     * @return: boolean
     * @throws
     **/
    public boolean menuCreate(List<JsonButtonEntity> buttons);
    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:21 2020/9/16
     *
     * @return: java.lang.String
     * @throws
     **/
    public String menuQuery();

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:21 2020/9/16
     *
     * @return: boolean
     * @throws
     **/
    public boolean menuDelete();

   /**
    * @author yanduohuang
    * @description //TODO
    * @date 19:21 2020/9/16
    *
    * @param: message
    * @return: java.lang.String
    * @throws
    **/
    public String receiveMessage(XmlMessageRequest message);
    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:21 2020/9/16
     *
     * @param: type
     * @param: path
     * @return: java.lang.String
     * @throws
     **/
    public String uploadTempMaterial(WxMediaType type, String path);

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:21 2020/9/16
     *
     * @param: media_id
     * @return: org.springframework.http.ResponseEntity<byte[]>
     * @throws
     **/
    public ResponseEntity<byte[]> downloadTempImage(String media_id);

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:21 2020/9/16
     *
     * @param: media_id
     * @return: java.lang.String
     * @throws
     **/
    public String downloadTempVideo(String media_id);

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:20 2020/9/16
     *
     * @param: media_id
     * @return: org.springframework.http.ResponseEntity<byte[]>
     * @throws
     **/
    public ResponseEntity<byte[]> downloadTempVoice(String media_id);

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:18 2020/9/16
     *
     * @param: type
     * @param: path
     * @param: title
     * @param: introduction
     * @return: java.lang.String
     * @throws
     **/
    public String uploadMaterial(WxMediaType type, String path, String title, String introduction);
    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:20 2020/9/16
     *
     * @param: media_id
     * @return: org.springframework.http.ResponseEntity<byte[]>
     * @throws
     **/
    ResponseEntity<byte[]> downloadMaterial(String media_id);

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:20 2020/9/16
     *
     * @param: media_id
     * @return: boolean
     * @throws
     **/
    boolean deleteMaterial(String media_id);

   /**
    * @author yanduohuang
    * @description //TODO
    * @date 19:20 2020/9/16
    *
    * @return: java.util.Map<java.lang.String,java.lang.Integer>
    * @throws
    **/
    Map<String, Integer> getMaterialCount();

    /**
     * @author yanduohuang
     * @description //TODO
     * @date 19:21 2020/9/16
     *
     * @throws
     **/
    void  batchGetMaterial();

}
