package com.laoh.demo;/**
 * @author hyd
 * @date 2020/9/28 20:32
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: TODO
 * @author yanduohuang
 * @date 2020/9/28 20:32
 * @version 1.0
 */
@Controller
public class MyController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
