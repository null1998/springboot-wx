package com.laoh.demo;

import com.laoh.core.IService;
import com.laoh.core.WxMediaType;
import com.laoh.core.dao.WxMessageStatusDao;
import com.laoh.core.entity.xml.XmlMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;


/**
 * 示例
 * @author hyd
 * @date 2020/7/17 22:17
 */
@Controller
@Slf4j
public class WeiXinController {
    @Autowired
    IService iService;
    @Autowired
    WxMessageStatusDao wxMessageStatusDao;

    /**
     * 接入认证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @GetMapping("/weixin")
    @ResponseBody
    public String checkSinnature(
                       @RequestParam(value = "signature") String signature,
                       @RequestParam(value = "timestamp") String timestamp,
                       @RequestParam(value = "nonce") String nonce,
                       @RequestParam(value = "echostr") String echostr) {
        return iService.checkSignature(signature, timestamp, nonce, echostr);
    }

    @RequestMapping(value="/weixin",method=RequestMethod.POST)
    @ResponseBody
    public String handlerMessage(@RequestBody XmlMessageRequest message){
        String result = iService.receiveMessage(message);
        log.info(result);
        return result;
    }

    @RequestMapping(value = "/uploadTempMaterial")
    @ResponseBody
    public void uploadTempMaterialTest(@RequestParam(value = "filePath") String filePath) {
        String media_id = iService.uploadTempMaterial(WxMediaType.IMAGE, filePath);
        log.info(media_id);
    }
    @RequestMapping(value = "/downloadTempImage")
    @ResponseBody
    public void downloadTempImageTest(@RequestParam(value = "media_id") String media_id,
                                      @RequestParam(value = "imagePath") String imagePath) {
        ResponseEntity<byte[]>entity = iService.downloadTempImage(media_id);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(imagePath);
            fileOutputStream.write(entity.getBody());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/downloadTempVideo")
    @ResponseBody
    public void downloadTempVideoTest(@RequestParam(value = "media_id") String media_id) {
        String url = iService.downloadTempVideo(media_id);
        log.info(url);
    }
    @RequestMapping(value = "/downloadTempVoice")
    @ResponseBody
    public void downloadTempVoiceTest(@RequestParam(value = "media_id") String media_id,
                                      @RequestParam(value = "voicePath") String voicePath) {
        ResponseEntity<byte[]>entity = iService.downloadTempVoice(media_id);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(voicePath);
            fileOutputStream.write(entity.getBody());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/uploadMaterial")
    @ResponseBody
    public void uploadMaterial(@RequestParam(value = "type") String type,
                               @RequestParam(value = "path") String path,
                               @RequestParam(value = "title") String title,
                               @RequestParam(value = "introduction") String introduction) {
        String resultString = iService.uploadMaterial(WxMediaType.VIDEO,path,title,introduction);
        log.info(resultString);

    }




}
