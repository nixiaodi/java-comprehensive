package org.jiang.threads.demo3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo03 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {

            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }
}
