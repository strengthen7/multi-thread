package com.anacmer.learn.frame;

import lombok.extern.slf4j.Slf4j;

/**
 * 一个线程栈内，每次方法调用都会产生一个栈帧。方法运行结束后，栈帧内存就会被释放掉
 * 栈帧内容包括：
 * 1.局部变量表：方法内用到的变量，在栈帧创建时分配空间，方法运行时，动态连接变量值
 * 2.返回地址：方法运行结束后返回到的地址，main 方法返回地址为空，表示线程结束
 * 3.锁记录：
 * 4.操作数栈
 */
@Slf4j
public class MethodFrame {
    // idea 的 debug 模式的 frames 区，就是线程的栈帧
    // 每个线程的栈帧都是独立的
    public static void main(String[] args) {
        new Thread("t1") {
            // t1 thread
            @Override
            public void run() {
                log.info(String.valueOf(add(1, 2)));
            }
        }.start();
        // main thread
        log.info(String.valueOf(add(3, 4)));
    }

    private static Integer add(Integer x, Integer y) {
        return x + y;
    }
}
