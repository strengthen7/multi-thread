package com.anacmer.learn.thread.rof;

import com.anacmer.learn.thread.SleepHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TwoParseTermination {

    public static void main(String[] args) {
        TwoParseTermination twoParseTermination = new TwoParseTermination();
        twoParseTermination.start();
        SleepHelper.sleep(2);
        twoParseTermination.stop();
    }

    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.info("monitor had been interrupted.");
                    log.info("please do something.");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.info("monitoring...");
                } catch (InterruptedException e) {
                    // 异常会清楚打断标记，则需要重新设置打断标记
                    currentThread.interrupt();
                    e.printStackTrace();
                }
            }
        }, "monitor");
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
