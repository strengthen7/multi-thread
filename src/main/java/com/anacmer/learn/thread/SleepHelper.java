package com.anacmer.learn.thread;

import java.util.concurrent.TimeUnit;

public class SleepHelper {

    public static void sleep(Integer secends) {
        try {
            TimeUnit.SECONDS.sleep(secends);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
