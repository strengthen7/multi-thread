package com.anacmer.learn.thread.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class ThreadCreateWay {

    public static void newThread() {
        // 推荐指定线程 名称
        Thread thread = new Thread("thread-1") {
            // 本质上也是 因为 thread 继承了 runnable 接口，可以实现 run 方法
            @Override
            public void run() {
                log.info("1 running...");
            }
        };
        thread.start();
        log.info("running...");
    }

    public static void implRunnable() {
        // runnable 更容易与线程池等高级 api 配合
        // runnable 让任务脱离 thread 的继承体系，更灵活
        Runnable runnable = () -> log.info("2 running...");
        // 参数 runnable 不为 null，就会执行 runnable 的 run 方法
        Thread thread = new Thread(runnable, "thread-2");
        thread.start();
        log.info("running...");
    }

    public static void implFutureTask() {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(1000);
            log.info("3 running...");
            return "hello world!";
        });
        Thread thread = new Thread(futureTask, "thread-3");
        thread.start();
        try {
            // 调用 get 方式时，会阻塞等待线程结果返回
            log.info(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info("running...");
    }

    // jstack pid 抓取某一时刻 java 进程的线程信息
    // jconsole 调用 jdk 自带的 java 进程管理器，可以监视 java 进程的相关信息

    public static void main(String[] args) {
        newThread();
        implRunnable();
        implFutureTask();
    }
}
