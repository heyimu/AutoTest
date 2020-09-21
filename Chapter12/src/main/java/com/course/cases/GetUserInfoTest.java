package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.utils.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取userId为1的信息")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession = DataBaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoTest = sqlSession.selectOne("getUserInfoCase", 1);
        System.out.println(getUserInfoTest.toString());
        System.out.println(TestConfig.getUserInfoUrl);

    }

}
