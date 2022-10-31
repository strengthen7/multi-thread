package com.anacmer.learn.thread.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadDaemon {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                // 可以在断点调试时，按住 alt+f8 启动 debug 语句执行器，中断线程
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.info("hello...");
        }, "daemon");
        // 如果主线程结束，那么守护线程也会立马结束(不管任务有没有执行完成
        thread.setDaemon(true);

        thread.start();
        log.info("shutdown...");
    }
}
