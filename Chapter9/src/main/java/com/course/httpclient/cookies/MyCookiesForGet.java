package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import sun.security.util.Resources;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

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
        uri = bundle.getString("test.get.with.cookies");
    }


    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String result;
        HttpGet get = new HttpGet(url + uri);
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(store);
        HttpResponse execute = client.execute(get);
        //获取响应码
        int statusCode = execute.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+statusCode);
        if (statusCode == 200){
            //获取相应结果
            result = EntityUtils.toString(execute.getEntity(), "UTF-8");
            System.out.println(result);
        }


    }
}
