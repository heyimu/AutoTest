package com.course.cases;

import org.json.JSONArray;
import org.json.JSONObject;
import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取userId为1的信息")
    public void getUserInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = sqlSession.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        //发送请求获取结果
        JSONArray result = getJsonResult(getUserInfoCase);

        //验证
        Thread.sleep(2000);
        User user = sqlSession.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        System.out.println("自己查库获取用户信息:"+user.toString());

        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        JSONObject jsonObject = new JSONObject(jsonArray);
        JSONObject jsonObject1 = new JSONObject(result);
        Assert.assertEquals(jsonObject.toString(),jsonObject1.toString());
    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id", getUserInfoCase.getUserId());

        post.setHeader("content-type", "application/json");

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
//        JSONArray jsonArray = JSONArray.parseArray(result);
        List resultArray = Arrays.asList(result);
        JSONArray jsonArray = new JSONArray(resultArray);
        return jsonArray;
    }

}
