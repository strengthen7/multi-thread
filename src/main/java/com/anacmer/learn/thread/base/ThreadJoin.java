package com.anacmer.learn.thread.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        testJoin();

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("t1 start...");
        t1.start();
        log.info("t2 start...");
        t2.start();
        long start = System.currentTimeMillis();
        log.info("t1 join...");
        t1.join();
        log.info("t2 join...");
        t2.join();
        long end = System.currentTimeMillis();
        // 理论上只需要等待两秒，因为线程都是同时开始运行的
        log.info("总共等待{}ms", end - start);
    }

    private static int value = 0;

    public static void testJoin() {

        Thread t1 = new Thread(() -> {
            value = 10;
            log.info("{}", value);
        });
        t1.start();
        log.info("{}", value);
        try {
            // join 方法会等待线程结束，这样打印的 value 才是赋值后的
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{}", value);
    }
}
