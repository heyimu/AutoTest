package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的信息")
    public void getUserListInfo() throws IOException, InterruptedException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        GetUserListCase getUserListCase = sqlSession.selectOne("getUserListCase", 0);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);

        //下边为写完接口的代码
        JSONArray resultJson = getJsonResult(getUserListCase);
        /**
         * 可以先讲
         */
        Thread.sleep(2000);
        List<User> userList = sqlSession.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u : userList){
            System.out.println("list获取的user:"+u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);

        Assert.assertEquals(userListJson.length(),resultJson.length());



        for(int i = 0;i<resultJson.length();i++){
//            JSONObject expect = (JSONObject) resultJson.get(i);
//            JSONObject actual = (JSONObject) userListJson.get(i);
            JSONObject object = new JSONObject(resultJson.get(0).toString());
            System.out.println("object：==============="+object.toString());
            JSONObject object1 = new JSONObject(userListJson.get(0));
            System.out.println("object1：==============="+object1.toString());
            Assert.assertEquals(object.toString(), object1.toString());
        }

    }

    private JSONArray getJsonResult(GetUserListCase userListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("username", userListCase.getUserName());
        param.put("age", userListCase.getAge());
        param.put("sex", userListCase.getSex());

        post.setHeader("content-type", "application/json");

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        JSONArray jsonArray = new JSONArray(result);

        return jsonArray;
    }
}
