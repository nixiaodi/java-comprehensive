package org.jiang.threads.demo5;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo5 {
    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.info("开始运行...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其他逻辑...");
            }
        },"t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.info("开始运行...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其他逻辑...");
            }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("唤醒其他线程...");
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
