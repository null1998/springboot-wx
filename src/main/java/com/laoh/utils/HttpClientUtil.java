package com.laoh.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author hyd
 * @date 2020/7/18 17:43
 */
@Slf4j
public class HttpClientUtil {


    @Data
    public static class HttpClientResult {
        private int code;
        private String content;

        public HttpClientResult(int code, String content) {
            this.code = code;
            this.content = content;
        }

    }

    public static final String ENCODING = "UTF-8";

    public static HttpClientResult doGet(String url) {
        return doGet(url, null, null);
    }
    public static HttpClientResult doGet(String url, Map<String,String> headers) {
        return doGet(url, headers, null);
    }
    public static HttpClientResult doGet(String url, Map<String,String> headers, Map<String,String> params){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        HttpClientResult result = null;
        try {
            httpClient = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                setParams(params,uriBuilder);
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            if (headers != null) {
                setHeaders(headers, httpGet);
            }
            result = getResult(httpClient, httpGet, httpResponse);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            release(httpClient,httpResponse);
        }
        return  result;
    }
    public static HttpClientResult doPost(String url) {
        return doPost(url, null, null);
    }
    public static HttpClientResult doPost(String url, Map<String,String> headers) {
        return doPost(url, headers, null);
    }
    public static HttpClientResult doPost(String url, Map<String,String> headers, Map<String,Object> entity)  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        HttpClientResult result = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            if (headers != null) {
                setHeaders(headers, httpPost);
            }
            if (entity != null) {
                setJsonEntity(entity, httpPost);
            }
            result = getResult(httpClient, httpPost, httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(httpClient,httpResponse);
        }

        return result;


    }
    public static HttpClientResult doPostMultipart(String url, String path) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        HttpClientResult result = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            File file = new File(path);
            FileBody bin = new FileBody(file);
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("bin", bin)
                    .build();
            httpPost.setEntity(reqEntity);
            result = getResult(httpClient, httpPost, httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(httpClient,httpResponse);
        }
        return result;
    }

    /**
     * 设置请求头
     * @param headers
     * @param httpMethod
     */
    public static void setHeaders(Map<String,String> headers, HttpRequestBase httpMethod) {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 设置请求参数，参数放在url后面
     * @param params
     * @param uriBuilder
     */
    public static void setParams(Map<String, String> params, URIBuilder uriBuilder) {
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uriBuilder.setParameter(entry.getKey(),entry.getValue());
            }
        }
    }

    /**
     * 设置Json格式请求体
     * @param entity
     * @param httpPost
     * @throws UnsupportedEncodingException
     */
    public static void setJsonEntity(Map<String, Object> entity, HttpPost httpPost) throws Exception {
        if (entity != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
            httpPost.setEntity(new StringEntity(jsonString, Charset.forName(ENCODING)));
        }
    }

    public static HttpClientResult getResult(CloseableHttpClient httpClient, HttpRequestBase httpMethod, CloseableHttpResponse httpResponse) throws IOException {
        httpResponse = httpClient.execute(httpMethod);
        if (httpResponse != null && httpResponse.getStatusLine() != null) {
            String content = "";
            if (httpResponse.getEntity() != null) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(),content);
        } else {
            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), "REQUEST_ERROR");
        }

    }
    public static void release(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        try {
            if (httpResponse != null) {
                httpResponse.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

