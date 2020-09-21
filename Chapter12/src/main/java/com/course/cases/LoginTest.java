package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFIle;
import com.course.utils.DataBaseUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
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
        Object loginCase = sqlSession.selectOne("loginCase", 1);
        System.out.println(loginCase);
        System.out.println(TestConfig.loginUrl);
    }


    @Test(groups = "loginFalse", description = "测试用户登录接口")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        Object loginCase = sqlSession.selectOne("loginCase", 20);
        System.out.println(loginCase);
        System.out.println(TestConfig.loginUrl);
    }






















}

