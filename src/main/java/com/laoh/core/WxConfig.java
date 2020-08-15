package com.laoh.core;

import com.laoh.utils.HttpClientUtil;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author hyd
 * @date 2020/7/24 22:23
 */
@Getter
@Setter
public class WxConfig {
    private static final String configFile = "/wx.properties";

    private String appId;
    private String appSecret;
    private String token;
    private String accessToken;
    private long expiresTime;
    private String packageName;
    private ExecutorService pool;
    private ConcurrentHashMap<String,Object> messageMap;

    /**
     * Msg注解注释的消息处理类
     */
    private Collection<Class<?>> handlers;
    /**
     * 微信消息类型字段与消息类型注解的映射
     * 例如CLICK-Click.class
     */
    private Map<String, Class<?>> msgActionMap;
    /**
     * 所有的消息类型注解，Click.class, Text.class等
     */
    private Set<Class<?>> msgActionSet;
    /**
     * 消息类型注解与其所注释的方法集合的映射
     */
    private Map<Class<?>, HashSet<Method>> annotationMethodsMap;

    private WxConfig() {
        Properties p = new Properties();
        try(InputStream inStream = this.getClass().getResourceAsStream(configFile)) {
            p.load(inStream);
            this.appId = p.getProperty("wx.appId");
            this.appSecret = p.getProperty("wx.appSecret");
            this.token = p.getProperty("wx.token");
            this.packageName = p.getProperty("wx.packageName");
            Integer corePoolSize = Integer.valueOf(p.getProperty("threadPool.corePoolSize"));
            Integer maximumPoolSize = Integer.valueOf(p.getProperty("threadPool.maximumPoolSize"));
            Integer keepAliveTime = Integer.valueOf(p.getProperty("theadPool.keepAliveTime"));
            Integer queueSize = Integer.valueOf(p.getProperty("threadPool.queueSize"));
            this.pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
            messageMap = new ConcurrentHashMap<>();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WxConfig getInstance(){
        return SingleTon.INSTANCE;
    }

    private static class SingleTon {
        private static final WxConfig INSTANCE = new WxConfig();
    }
    public String takeAccessToken() {
        return takeAccessToken(false);
    }

    public String takeAccessToken(boolean refresh) {
        if (SingleTon.INSTANCE == null) {
            return "";
        }
        if (refresh) {
            expireAccessToken();
        }
        if (isAccessTokenExpired()) {
            synchronized (this) {
                if (isAccessTokenExpired()) {
                    String url = WxConstants.URL_GET_ACCESSTOEKN
                            .replace("APPID", getAppId())
                            .replace("APPSECRET", getAppSecret());
                    HttpClientUtil.HttpClientResult result = null;
                    try {
                        result = HttpClientUtil.doGet(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (result.getCode() == 200) {
                        JSONObject jsonObject = new JSONObject(result.getContent());
                        String accessToken = jsonObject.getString("access_token");
                        int expiredSecond = Integer.valueOf(jsonObject.getInt("expires_in"));
                        updateAccessToken(accessToken, expiredSecond);
                    }
                }
            }
        }
        return getAccessToken();
    }

    /**
     * accessToken强制失效
     */
    public void expireAccessToken() {
        this.expiresTime = 0;
    }

    /**
     * 判断accessToken是否过期
     * @return
     */
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }
    /**
     * 用新的accessToken替换旧的accessToken，更新accessToken失效时间
     * @param accessToken
     * @param expiresInSeconds
     */
    private void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }

}
