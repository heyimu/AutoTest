package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.security.util.Resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private String uri;
    private ResourceBundle bundle;

    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = Resources.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
        uri = bundle.getString("getCookies.uri");

    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //测试逻辑代码书写
        HttpGet get = new HttpGet(url + uri);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse execute = client.execute(get);
        result = EntityUtils.toString(execute.getEntity(), "UTF-8");
        System.out.println(result);

        store = client.getCookieStore();
        List<Cookie> cookies = store.getCookies();
        for (Cookie cookie : cookies) {

            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name = " + name + " , " + "value= " + value);
        }
        bundle = Resources.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
        uri = bundle.getString("test.post.with.cookies");
    }


    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        //配置测试地址
        String testUrl = url + uri;

        //声明 client对象
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个post对象
        HttpPost post  = new HttpPost(testUrl);
        //添加参数
        JSONObject json = new JSONObject();
        json.put("name","huhansan");
        json.put("age","18");
        //设置请求信息 hear
        post.setHeader("content-type","application/json");
        //设置请求参数
        StringEntity entity = new StringEntity(json.toString(),"UTF-8");
        post.setEntity(entity);
        //设置cookies信息
        client.setCookieStore(store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);
        //结果值
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200){
            JSONObject jsonObject = new JSONObject(s);
            String name = (String) jsonObject.get("huhansan");
            String status = (String) jsonObject.get("status");
            Assert.assertEquals("success",name);
            Assert.assertEquals("1",status);

        }


    }
}
