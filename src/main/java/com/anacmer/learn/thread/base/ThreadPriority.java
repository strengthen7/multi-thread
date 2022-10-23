package com.anacmer.learn.thread.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadPriority {
    public static void main(String[] args) {
        // 数字越大，优先级越高
        // 仅仅是一个提示，具体执行还是要看任务调度器
        Thread t1 = new Thread(() -> {
            for (int i = 0; ; i++) {
                log.info("t1----->{}", i);
            }

        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; ; i++) {
                Thread.yield();
                log.info("        t2----->{}", i);
            }

        }, "t2");
        t1.start();
        t2.start();
        t1.setPriority(Integer.MIN_VALUE);
        t2.setPriority(Integer.MAX_VALUE);
        // 测试结果，yield 效果明细，priority 不明显（可能是我核心数太多了？
        // 总结就是 都是不靠谱的手段，最终调度结果还是要看任务调度器
    }
}
