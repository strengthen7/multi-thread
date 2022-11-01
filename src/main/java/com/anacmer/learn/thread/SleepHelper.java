package com.anacmer.learn.thread;

import java.util.concurrent.TimeUnit;

public class SleepHelper {

    public static void sleep(Integer seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
