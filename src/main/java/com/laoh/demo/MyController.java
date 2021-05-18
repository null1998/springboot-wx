package com.laoh.demo;/**
 * @author hyd
 * @date 2020/9/28 20:32
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: TODO
 * @author yanduohuang
 * @date 2020/9/28 20:32
 * @version 1.0
 */
@Controller
@RequestMapping("my")
public class MyController {
    @RequestMapping("hello")
    public String hello(ModelMap map) {
        map.put("str", "hello");
        System.out.println("hello");
        return "login";
    }
}
