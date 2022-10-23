package com.anacmer.learn.thread.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadState {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("t1 niubi...");
            try {
                // sleep 方法会让线程从 RUNNING 状态转变为 TIMED_WAITING
                Thread.sleep(2000);
                // yield 方式会让线程从 RUNNING 状态转换为 RUNNABLE，等待线程再次被调度
                Thread.yield();
                log.info("t1 continue running...");
            } catch (InterruptedException e) {
                log.warn("t1 was interrupted...");
                throw new RuntimeException(e);
            }
        }, "t1");
        // 直接调用 run 方法，相当于主线程 main 调用对象方法，不会创建线程
        // t1.run();
        // 新创建的线程是 NEW 状态
        log.info("{}", t1.getState());
        t1.start();
        // 调用 start 方法后是， RUNNABLE 状态，告诉操作系统可以被执行，但是并没有立即被执行
        log.info("{}", t1.getState());
        try {
            // 可以使用 TimeUnit 类代替 Thread.sleep()，因为声明了单位，可读性更好一些,源码也就是转换为 Thread.sleep()
            TimeUnit.MILLISECONDS.sleep(1000);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("{}", t1.getState());
        // 中断线程，如果线程处于 sleep 状态，会响应中断，运行状态则不会
        t1.interrupt();
        // 异常后，线程结束
        log.info("{}", t1.getState());

    }
}
