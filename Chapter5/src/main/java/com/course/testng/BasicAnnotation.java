package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    //最基本的注释，用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
        System.out.printf("Thread ID: %s%n",Thread.currentThread().getId()+"===========================================");

    }

    @Test
    public void testCase2(){
        System.out.println("测试用例2");
        System.out.printf("Thread ID: %s%n",Thread.currentThread().getId()+"===========================================");

    }

//    @BeforeMethod
//    public void beforeMethod(){
//        System.out.println("BeforeMethod在测试方法执行前，执行的");
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        System.out.println("afterMethod在测试方法之后，执行的");
//    }
//
//    @BeforeClass
//    public void beforeClass(){
//        System.out.println("类运行之前");
//    }
//
//    @AfterClass
//    public void afterClass(){
//        System.out.println("类运行之后");
//    }
//
//    @BeforeSuite
//    public void beforeSuite(){
//        System.out.println("beforeSuite");
//    }
//
//    @AfterSuite
//    public void afterSuite(){
//        System.out.println("afterSuite");
//    }
}
