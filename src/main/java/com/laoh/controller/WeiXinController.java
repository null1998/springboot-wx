package com.laoh.controller;

import com.laoh.core.IService;
import com.laoh.core.dao.WxMessageStatusDao;
import com.laoh.core.entity.xml.XmlMessageRequest;
import com.laoh.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


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
     * @param request
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @GetMapping("/weixin")
    public void checkSinnature(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(value = "signature", required = true) String signature,
                       @RequestParam(value = "timestamp", required = true) String timestamp,
                       @RequestParam(value = "nonce", required = true) String nonce,
                       @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                log.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            log.error("出错");
        }
    }
    @RequestMapping(value="/weixin",method=RequestMethod.POST, produces = "application/xml")
    @ResponseBody
    public String handlerMessage(@RequestBody XmlMessageRequest message){

            return iService.receiveMessage(message);
    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public void test() {


    }



}
