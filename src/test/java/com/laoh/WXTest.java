package com.laoh;

import com.laoh.core.IService;
import com.laoh.core.WxConfig;
import com.laoh.core.WxMediaType;
import com.laoh.core.WxService;
import com.laoh.core.entity.json.JsonButtonEntity;
import com.laoh.core.utils.IoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;
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
        List<JsonButtonEntity> buttons = new ArrayList<>();

        List<JsonButtonEntity> sub_button1 = new LinkedList<>();
        sub_button1.add(new JsonButtonEntity()
                .setName("点击1")
                .setType("click")
                .setKey("key1_1"));
        sub_button1.add(new JsonButtonEntity()
                .setName("点击2")
                .setType("click")
                .setKey("key1_2"));
        buttons.add(new JsonButtonEntity()
                .setName("点击").setSub_button(sub_button1));

        List<JsonButtonEntity> sub_button2 = new LinkedList<>();
        sub_button2.add(new JsonButtonEntity()
               .setName("百度")
               .setType("view")
               .setUrl("http://www.baidu.com/"));
        sub_button2.add(new JsonButtonEntity()
                .setName("哔哩哔哩")
                .setType("view")
                .setUrl("https://www.bilibili.com/"));
        buttons.add(new JsonButtonEntity()
                .setName("链接").setSub_button(sub_button2));
        wxService.menuCreate(buttons);

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
            byte[] bytes = IoUtil.readFileToByteArray(fileName);
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
    @Test
    void uploadMaterialTest() {
        String media  = wxService.uploadMaterial(WxMediaType.IMAGE, "C:\\Users\\test\\material\\card.png","","");
        System.out.println(media);
    }
    @Test
    void downloadMaterialTest() {
        ResponseEntity<byte[]> responseEntity = wxService.downloadMaterial("sMX4aUP6bkedcm0XoxrYQYnf6JXhOCHdns-ZRBv1hSc");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\test\\m1.jpg");
            fileOutputStream.write(responseEntity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
