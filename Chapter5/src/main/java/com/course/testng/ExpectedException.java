package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {
    /**
     * 什么时候使用异常测试？
     *      期望的结果是个异常
     *      比如传入的参数是不合法的 程序会抛异常
     *      我们希望的结果是这个异常
     */

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeException(){
        System.out.println("runTimeException 执行了");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("runTimeException 成功了");
        throw  new RuntimeException();
    }
}
