package org.jiang.threads.demo4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo04 {
    private static int num;

    private static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                num++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                num--;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.info(String.valueOf(num));
    }
}
