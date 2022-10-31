package com.anacmer.learn.thread.base;

import com.anacmer.learn.thread.SleepHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ThreadPark {
    public static void main(String[] args) {
        interruptPark();
    }

    private static void interruptPark() {

        Thread thread = new Thread(() -> {
            log.info("park...");
            // 调用也是一个 native 方法
            LockSupport.park();
            log.info("un park...");
            log.info("interrupted status = {}", Thread.currentThread().isInterrupted());
            // 如果 打断标记 为 true，则不会再执行 park 操作
            LockSupport.park();
        });
        thread.start();

        SleepHelper.sleep(1);
        thread.interrupt();

    }
}
