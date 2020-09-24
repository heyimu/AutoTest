package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFIle;
import com.course.utils.DataBaseUtil;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTest",description = "测试准备工作，获取httpclient对象")
    public void beforeTest(){
        TestConfig.getUserListUrl = ConfigFIle.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.getUserInfoUrl = ConfigFIle.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.addUserUrl = ConfigFIle.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.loginUrl = ConfigFIle.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFIle.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();

    }

    @Test(groups = "loginTrue", description = "测试用户登录接口")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 1);
        System.out.println("loginTrue----->"+loginCase);
        System.out.println(TestConfig.loginUrl);

        //发送请求
        String result = getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(), result);

    }


    @Test(groups = "loginFalse", description = "测试用户登录接口")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 2);
        System.out.println("loginFalse----->"+loginCase);
        System.out.println(TestConfig.loginUrl);
        //发送请求
        String result = getResult(loginCase);
        //验证结果
        Assert.assertEquals(loginCase.getExpected(), result);
    }


    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("实际结果："+result);
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }



}

