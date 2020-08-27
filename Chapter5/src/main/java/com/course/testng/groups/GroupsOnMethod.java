package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端组的测试方法111111111");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端组的测试方法222222222");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("这是客户端组的测试方法333333333");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.println("这是客户端组的测试方法444444444");
    }

    @BeforeGroups(value = "server")
    public void beforeGroupsOnServer(){
        System.out.println("服务端组运行之前的执行的方法!!!!!!!!!");
    }

    @AfterGroups(value = "server")
    public void afterGroupsOnServer(){
        System.out.println("服务端组运行之后的执行的方法!!!!!!!!!!");
    }

    @BeforeGroups(value = "client")
    public void beforeGroupsOnClient(){
        System.out.println("客户端组运行之前的执行的方法!!!!!!!!!");
    }

    @AfterGroups(value = "client")
    public void afterGroupsOnClient(){
        System.out.println("客户端组运行之后的执行的方法!!!!!!!!!!");
    }
}
