package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public void stu1(){
        System.out.println("GroupsOnClass2中的stu1执行了!!!!");
    }
    public void stu2(){
        System.out.println("GroupsOnClass2中的stu2执行了!!!!");
    }
}
