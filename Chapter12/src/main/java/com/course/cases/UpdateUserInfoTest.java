package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import com.sun.javafx.binding.StringFormatter;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class  UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void updateUserInfo() throws Exception {

        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 1);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求获取信息
        int result = getResult(updateUserInfoCase);

        Thread.sleep(3000);

        //验证
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);



    }


    @Test(dependsOnGroups = "loginTrue", description = "删除用户信息")
    public void deleteUser() throws IOException, InterruptedException {

        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 2);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);

        //发送请求获取信息
        int result = getResult(updateUserInfoCase);

        Thread.sleep(3000);

        //验证
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
        System.out.println("==========================");
        System.out.println(user.toString());
        System.out.println("==========================");
        Assert.assertNotNull(result);
        Assert.assertNotNull(user);

    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",updateUserInfoCase.getUserId());
        jsonObject.put("userName",updateUserInfoCase.getUserName());
        jsonObject.put("sex",updateUserInfoCase.getSex());
        jsonObject.put("age",updateUserInfoCase.getAge());
        jsonObject.put("permission",updateUserInfoCase.getPermission());
        jsonObject.put("isDelete",updateUserInfoCase.getIsDelete());

        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        return Integer.parseInt(result);
    }

}
