package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * 测试套件配置类
 */
public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before Suite执行");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("after Suite执行");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest==执行了");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest--执行了");
    }
}
