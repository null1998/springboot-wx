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
        JsonButtonEntity button1 = new JsonButtonEntity();
        button1.setType("click");
        button1.setName("b站热门");
        button1.setKey("KEY_1");
        JsonButtonEntity button2 = new JsonButtonEntity();
        button2.setName("菜单");
        List<JsonButtonEntity> sub_button = new LinkedList<>();
        JsonButtonEntity button2_1 = new JsonButtonEntity();
        button2_1.setName("搜索");
        button2_1.setType("view");
        button2_1.setUrl("http://www.baidu.com/");
        sub_button.add(button2_1);
        JsonButtonEntity button2_2 = new JsonButtonEntity();
        button2_2.setName("赞一下我们");
        button2_2.setType("click");
        button2_2.setKey("KEY_2_1");
        sub_button.add(button2_2);
        button2.setSub_button(sub_button);
        List<JsonButtonEntity> buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        System.out.println(wxService.menuCreate(buttons));
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
