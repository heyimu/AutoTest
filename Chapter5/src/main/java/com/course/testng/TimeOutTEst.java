package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTEst {
    @Test(timeOut = 3000)
    public void testSuccess(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(timeOut = 1000)
    public void testFailed() throws InterruptedException {
        Thread.sleep(1500);
    }

}
