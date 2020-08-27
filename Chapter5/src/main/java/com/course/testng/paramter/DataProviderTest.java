package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age){
        System.out.println("name= " + name + ";  age=" + age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData(){
        Object[][] objects = new Object[][]{
                {"张三",10},
                {"王五",15},
                {"李四",30}
        };
        for (Object[] object : objects) {
            for (Object o : object) {
                System.out.println(o);
            }
            System.out.println("--------------------");
        }
        return objects;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age){
        System.out.println("test1111:name="+name+";age="+age);
    }
    @Test(dataProvider = "methodData")
    public void test2(String name, int age){
        System.out.println("test2222:name="+name+";age="+age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodTest(Method method){
        Object[][] result = null;

        if (method.getName().equals("test1")){
            result = new Object[][]{
                    {"张三",20},
                    {"李四",23}
            };
        }else if (method.getName().equals("test2")){
            result = new Object[][]{
                    {"王五",60},
                    {"赵六",73}
            };
        }

        return result;
    }

}
