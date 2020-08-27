package com.tester.extend.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestDemo {

    @Test
    public void test(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void test1(){
        Assert.assertEquals(1,12);
    }

    @Test
    public void test2(){
        Assert.assertEquals("aa","aa");
    }

    @Test
    public void logtest(){
        Reporter.log("日志test");
        throw new RuntimeException("demo Exception");
    }

}
