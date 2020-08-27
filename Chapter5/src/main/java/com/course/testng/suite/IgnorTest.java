package com.course.testng.suite;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

public class IgnorTest {

    @Test
    public void ignre1() {
        System.out.println("ignre1_ignre1执行了");
    }

    @Test(enabled = false)
    public void ignre2() {
        System.out.println("ignre2_ignre2执行了");
    }
    @Test(enabled = true)
    public void ignre3() {
        System.out.println("ignre3_ignre3执行了");
    }

}
