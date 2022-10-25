package com.anacmer.learn.thread.base;

import com.anacmer.learn.thread.SleepHelper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadInterrupt {
    public static void main(String[] args) {
        // 打断一个正在运行的线程，中断只是一个信号量，具体什么时候打断，由线程本身决定
        interruptSleepWaitJoinThread();
        interruptNormalThread();
    }

    public static void interruptSleepWaitJoinThread() {
        Thread thread = new Thread(() -> {
            log.info("i am t1.");
            SleepHelper.sleep(5);
        }, "t1");

        thread.start();
        log.info("t1 is running...");
        SleepHelper.sleep(1);
        // 打断 sleep wait join 状态的线程
        thread.interrupt();
        // 线程打断标记
        log.info("t1 has been interrupted ?{}", thread.isInterrupted());
    }

    public static void interruptNormalThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    log.info("the thread t1 has been interrupted.");
                    break;
                }
            }
        }, "t1");

        thread.start();
        log.info("thread running...");
        thread.interrupt();
        // 线程打断标记
        log.info("t1 has been interrupted ?{}", thread.isInterrupted());
    }
}
