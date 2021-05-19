package com.laoh.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoh.core.annotation.Match;
import com.laoh.core.annotation.ResponseXml;
import com.laoh.core.entity.DuplicateRemovalMessage;
import com.laoh.core.entity.json.JsonButtonEntity;
import com.laoh.core.entity.xml.XmlMessageRequest;
import com.laoh.core.entity.xml.XmlResponse;
import com.laoh.core.exception.WxException;
import com.laoh.core.utils.HttpClientUtil;
import com.laoh.core.utils.JaxbUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author hyd
 * @date 2020/7/23 19:40
 */
@Slf4j
@Component
public class WxService implements IService{

    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 接入认证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echo
     * @return
     */
    @Override
    public String checkSignature(String signature, String timestamp, String nonce, String echo) {
        String token = WxConfig.getInstance().getToken();
        String[] arr = new String[] { token, timestamp, nonce };
        // 将 token、timestamp、nonce 三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance(WxConstants.SHA_1);
            // 将三个参数字符串拼接成一个字符串进行 sha1 加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将 sha1 加密后的字符串可与 signature 对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) ? echo : "" : "";
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param byteArray
     * @return
     */
    private String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * @param mByte
     * @return
     */
    private String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    /**
     * 获取微信callback IP地址
     * @return
     */
    @Override
    public List<Object> getCallBackIp() {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_GET_CALL_BACK_IP.replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doGet(url);
        JSONObject jsonObject = new JSONObject(result.getContent());
        List<Object> ipList = jsonObject.getJSONArray(WxConstants.IP_LIST).toList();
        return ipList;
    }

    @Override
    public List<Object> getApiDomainIp() {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_GET_API_DOMAIN_IP.replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doGet(url);
        JSONObject jsonObject = new JSONObject(result.getContent());
        List<Object> ipList = jsonObject.getJSONArray(WxConstants.IP_LIST).toList();
        return ipList;
    }

    /**
     * 将button以json的格式加入到post请求体中
     * @param buttons 一级菜单，最多三个
     * @return 创建菜单是否成功
     */
    @Override
    public boolean menuCreate(List<JsonButtonEntity> buttons) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_CREATE_MENU.replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken);
        Map<String, Object> entityMap = new HashMap<>();
        entityMap.put(WxConstants.BUTTON,buttons);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doPost(url,null,entityMap);
        log.info("创建自定义菜单："+result.getContent());
        JSONObject jsonObject = new JSONObject(result.getContent());
        return Integer.parseInt(jsonObject.get(WxConstants.ERRCODE).toString()) == 0;
    }

    /**
     * @return 自定义菜单的配置，json格式
     */
    @Override
    public String menuQuery() {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_GET_CURRENT_MENU_INFO.replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doGet(url);
        log.info("查询当前菜单配置："+result.getContent());
        return result.getContent();
    }

    /**
     * 删除菜单
     * @return
     */
    @Override
    public boolean menuDelete() {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_DELETE_MENU.replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doGet(url);
        log.info("删除菜单");
        JSONObject jsonObject = new JSONObject(result.getContent());
        return Integer.parseInt(jsonObject.get(WxConstants.ERRCODE).toString()) == 0;
    }

    /**
     * 针对不同的消息，找到最匹配的方法，
     * 拿到返回结果，转换为xml格式或直接返回普通字符串
     * @param message
     * @return
     */
    @SneakyThrows
    @Override
    public String receiveMessage(XmlMessageRequest message) {
        log.info(message.toString());
        //消息的类型，如text，click
        String msgAction;
        //消息的标识，普通消息使用msgId，事件使用FromUserName+CreateTime
        String msgKey;
        if (message.getMsgType().equals(WxConstants.XML_MSG_EVENT)) {
            msgAction = message.getEvent();
            msgKey = message.getFromUserName() + message.getCreateTime();
        } else {
            msgAction = message.getMsgType();
            msgKey = String.valueOf(message.getMsgId());
        }
        ValueOperations<String, DuplicateRemovalMessage> operations = redisTemplate.opsForValue();
        if (operations.get(msgKey) == null) {
            operations.set(msgKey, new DuplicateRemovalMessage(message.getMsgId(),message.getFromUserName(),message.getToUserName()),15, TimeUnit.SECONDS);
            WxConfig wxConfig = WxConfig.getInstance();
            //利用msgAction找出对应的注解，例如msgAction为CLICK,则对应注解为Click.class
            Class annotation= wxConfig.getMsgActionMap().get(msgAction);
            //将所有包含特定注解的方法从map中取出来
            HashSet<Method> methods = wxConfig.getAnnotationMethodsMap().get(annotation);
            Method matchMethod = bestMatch(methods, message, msgAction, annotation);
            if (matchMethod == null) {
                throw new WxException("没有处理消息"+message+"的方法");
            }
            log.info("开启异步任务");
            Object response = matchMethod.invoke(matchMethod.getDeclaringClass().newInstance(), message);
            String result = null;
            if (matchMethod.isAnnotationPresent(ResponseXml.class)) {
                if (response instanceof XmlResponse) {
                    result = JaxbUtil.toXml(response);
                } else {
                    throw new WxException("不是XmlResponse子类，无法转化为xml");
                }
            } else {
                result = response.toString();
            }
            WxConfig.getInstance().getMessageResult().put(msgKey, result);
            return result;
        } else {
            while(true) {
                if (WxConfig.getInstance().getMessageResult().get(msgKey) != null) {
                    return WxConfig.getInstance().getMessageResult().get(msgKey);
                }
            }
        }

    }

    /**
     *私有方法，用于帮助不同消息找到匹配方法
     * @param methods 消息处理注解对应方法集合
     * @param message 消息
     * @param msgAction 消息类型，例如text，CLICK
     * @param annotation 消息处理注解，例如Text.class,Click.class
     * @return 执行方法
     */
    private Method bestMatch(Set<Method> methods, XmlMessageRequest message, String msgAction, Class annotation) throws Exception {
        Method bestMatch = null;
        Class c = new MethodMatcher().getClass();
        for (Method m : c.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Match.class)) {
                //取出MethodMatcher方法上Match注解的值
                String str = (String) m.getAnnotation(Match.class).annotationType().getMethod("msgAction").invoke(m.getAnnotation(Match.class));
                if (str.equals(msgAction)) {
                    bestMatch = (Method) m.invoke(c.newInstance(), methods, message, annotation);
                    return bestMatch;
                }
            }
        }
        for (Method m : methods) {
            bestMatch = m;
            break;
        }
        return bestMatch;
    }

    /**
     * 图片10xRcLjvTPrldtgUSJeeS4S_X-iMXMCK52OBt_lFeUMzLpfiRdaB3PLVg9NDGtVp
     * 语音OTzhzjqg8vhnGdyUcrIktzoPVFizYUh1CRx3XmzfB8H7KrKOpRYRux2wnPDgy53d
     * 视频1Clzh7BjYvGM1MVINrBqt9Vc-684YLgZOT77snb3w7uu_DrQyXYoQkiDjIXBbV6L
     * 将临时素材用post form-data/multipart方式上传
     * @param type 媒体文件类型
     * @param path 媒体文件路径
     */
    @Override
    public String uploadTempMaterial(WxMediaType type, String path) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_UPLOAD_TEMP_MEDIA.
                replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken)
                .replace(WxConstants.URL_PARAMTER_TYPE,type.getName());

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        form.add(WxConstants.MEDIA, fileSystemResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> data = new HttpEntity<>(form, headers);
        try{
            String resultString = restTemplate.postForObject(url, data, String.class);
            if(!StringUtils.isEmpty(resultString)){
                JSONObject jsonObject = new JSONObject(resultString);
                return jsonObject.getString(WxConstants.MEDIA_ID);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadTempImage(String media_id) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_DOWNLOAD_TEMP_MEDIA.
                replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken)
                .replace(WxConstants.URL_PARAMTER_MEDIA_ID,media_id);
        String fileName = media_id+ ".jpg";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(WxConstants.ATTACHMENT, fileName);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url,HttpMethod.GET,httpEntity,byte[].class);
        return responseEntity;
    }

    @Override
    public String downloadTempVideo(String media_id) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_DOWNLOAD_TEMP_MEDIA.
                replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken)
                .replace(WxConstants.URL_PARAMTER_MEDIA_ID,media_id);
        String responseString = restTemplate.getForObject(url,String.class);
        return responseString;
    }

    @Override
    public ResponseEntity<byte[]> downloadTempVoice(String media_id) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_DOWNLOAD_TEMP_MEDIA.
                replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken)
                .replace(WxConstants.URL_PARAMTER_MEDIA_ID,media_id);
        String fileName = media_id+ ".mp3";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData(WxConstants.ATTACHMENT, fileName);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url,HttpMethod.GET,httpEntity,byte[].class);
        return responseEntity;
    }

    /**
     * 图片sMX4aUP6bkedcm0XoxrYQYnf6JXhOCHdns-ZRBv1hSc
     * 语音sMX4aUP6bkedcm0XoxrYQYjXjC-THTG59P-ZRB_pESM
     * 视频
     * @param type
     * @param path
     * @param title
     * @param introduction
     * @return
     */
    @Override
    public String uploadMaterial(WxMediaType type, String path, String title, String introduction) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        if (accessToken != null) {
            String url = WxConstants.URL_UPLOAD_MATERIAL_MEDIA.replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken)
                    .replace(WxConstants.URL_PARAMTER_TYPE, type.getName());

            //设置请求体，注意是LinkedMultiValueMap
            MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
            if(WxConstants.MEDIA_TYPE_VIDEO.equalsIgnoreCase(type.getName())){
                if(!StringUtils.isEmpty(title) && !StringUtils.isEmpty(introduction)){
                    HashMap<String, String> map = new HashMap<>();
                    map.put("title", title);
                    map.put("introduction", introduction);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    String jsonString = null;
                    try {
                        jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    data.add("description", jsonString);
                }
            }

            //设置上传文件
            FileSystemResource fileSystemResource = new FileSystemResource(path);
            data.add(WxConstants.MEDIA, fileSystemResource);

            //上传文件,设置请求头
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            httpHeaders.setContentLength(fileSystemResource.getFile().length());

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(data,
                    httpHeaders);
            try{
                String resultString = restTemplate.postForObject(url, requestEntity, String.class);
                return resultString;
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        return null;
    }


    @Override
    public ResponseEntity<byte[]> downloadMaterial(String media_id) {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_DOWNLOAD_MATERIAL_MEDIA.
                replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken);
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add(WxConstants.MEDIA_ID, media_id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentDispositionFormData(WxConstants.ATTACHMENT, "s.jpg");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(data, headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url,HttpMethod.POST,httpEntity,byte[].class);
        return responseEntity;
    }

    @Override
    public boolean deleteMaterial(String media_id) {
        return false;
    }

    @Override
    public Map<String, Integer> getMaterialCount() {
        String accessToken = WxConfig.getInstance().takeAccessToken();
        String url = WxConstants.URL_GET_MATERIAL_COUNT.replace(WxConstants.URL_PARAMTER_ACCESS_TOKEN, accessToken);
        HttpClientUtil.HttpClientResult  result = HttpClientUtil.doGet(url);
        JSONObject jsonObject = new JSONObject(result.getContent());
        Map<String, Integer> map = new HashMap<>();
        map.put(WxConstants.VOICE_COUNT,jsonObject.getInt(WxConstants.VOICE_COUNT));
        map.put(WxConstants.VIDEO_COUNT,jsonObject.getInt(WxConstants.VIDEO_COUNT));
        map.put(WxConstants.IMAGE_COUNT,jsonObject.getInt(WxConstants.IMAGE_COUNT));
        map.put(WxConstants.NEWS_COUNT,jsonObject.getInt(WxConstants.NEWS_COUNT));
        log.info(map.toString());
        return map;
    }

    @Override
    public void batchGetMaterial() {

    }


}
