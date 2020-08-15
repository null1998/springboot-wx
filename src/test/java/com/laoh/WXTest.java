package com.laoh;

import com.laoh.core.IService;

import com.laoh.core.WxConfig;
import com.laoh.core.WxService;
import com.laoh.core.entity.json.JsonButtonEntity;
import com.laoh.utils.IOUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

/**
 * @author hyd
 * @date 2020/7/19 22:05
 */

public class WXTest {

    IService wxService = new WxService();
    HashMap hashMap;

    @Test
    void menuCreateTest() {
        //一级菜单
        JsonButtonEntity button1 = new JsonButtonEntity();
        button1.setName("点击");
        List<JsonButtonEntity> sub_button1 = new LinkedList<>();
        //二级菜单
        JsonButtonEntity button1_1 = new JsonButtonEntity();
        button1_1.setName("点击1");
        button1_1.setType("click");
        button1_1.setKey("key1_1");
        sub_button1.add(button1_1);
        JsonButtonEntity button1_2 = new JsonButtonEntity();
        button1_2.setName("点击2");
        button1_2.setType("click");
        button1_2.setKey("key1_2");
        sub_button1.add(button1_2);
        button1.setSub_button(sub_button1);


        //一级菜单
        JsonButtonEntity button2 = new JsonButtonEntity();
        button2.setName("链接");
        List<JsonButtonEntity> sub_button2 = new LinkedList<>();
        //二级菜单
        JsonButtonEntity button2_1 = new JsonButtonEntity();
        button2_1.setName("百度");
        button2_1.setType("view");
        button2_1.setUrl("http://www.baidu.com/");
        sub_button2.add(button2_1);
        JsonButtonEntity button2_2 = new JsonButtonEntity();
        button2_2.setName("哔哩哔哩");
        button2_2.setType("view");
        button2_2.setUrl("https://www.bilibili.com/");
        sub_button2.add(button2_2);
        button2.setSub_button(sub_button2);

        //整合所有一级菜单
        List<JsonButtonEntity> buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        wxService.menuCreate(buttons);
        System.out.println();
    }
    @Test
    void menuQueryTest() {
        wxService.menuQuery();
    }
    @Test
    void menuDeleteTest() {
        System.out.println(wxService.menuDelete());
    }

    @Test
    void test() {
        File file = new File("C:\\Users\\test\\Desktop\\ju.jpg");
        if (file.exists()) {
            String fileName = "C:\\Users\\test\\Desktop\\ju.jpg";
            byte[] bytes = IOUtil.readFileToByteArray(fileName);
            System.out.println(Arrays.toString(bytes));
        }
    }
    @Test
    void wxConfigTest() {
        WxConfig config = WxConfig.getInstance();
        System.out.println(config.getAppId());
        System.out.println(config.getAppSecret());
        System.out.println(config.getAccessToken());
        WxConfig.getInstance().getAccessToken();
        System.out.println(config.getAccessToken());
        System.out.println(config.getExpiresTime());
    }
    @Test
    void classScanTest() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

    }

}
