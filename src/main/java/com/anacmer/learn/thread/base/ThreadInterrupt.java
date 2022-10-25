package com.anacmer.learn.thread.base;

import com.anacmer.learn.thread.SleepHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterrupt {

    // https://www.cnblogs.com/onlywujun/p/3565082.html 线程中断的具体解释
    public static void main(String[] args) {
        // 打断一个正在运行的线程，中断只是一个信号量，具体什么时候打断，由线程本身决定
        interruptSleepWaitJoinThread();
        interruptNormalThread();
    }

    public static void interruptSleepWaitJoinThread() {
        Thread thread = new Thread(() -> {
            log.info("i am t1.");
            try {
                Thread.sleep(2000);
                // 抛出InterruptedException异常后，中断标示位会自动清除
            } catch (InterruptedException e) {
                log.info("interrupted status ? {}", Thread.currentThread().isInterrupted());
                throw new RuntimeException(e);
            }
        }, "t1");

        thread.start();
        log.info("t1 is running...");
        SleepHelper.sleep(1);
        // 打断 sleep wait join 状态的线程
        thread.interrupt();
        // 线程打断标记
        log.info("interrupted status ? {}", thread.isInterrupted());
    }

    public static void interruptNormalThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                log.info("i am running.");
                if (Thread.currentThread().isInterrupted()) {
                    log.info("the thread t1 has been interrupted.");
                    break;
                }
            }
        }, "t1");

        thread.start();
        log.info("thread start.");
        thread.interrupt();
        // 线程打断标记
        log.info("interrupted status ? {}", thread.isInterrupted());
    }
}


