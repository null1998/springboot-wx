package com.laoh.controller;

import com.laoh.core.IService;
import com.laoh.core.dao.WxMessageStatusDao;
import com.laoh.core.entity.xml.XmlMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
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
        return iService.receiveMessage(message);
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public void test() {


    }



}
