package org.jiang.threads.demo3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo3 {
    private static int r;
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            log.info("开始");
            try {
                TimeUnit.SECONDS.sleep(10);
                r = 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("结束");
        });
        thread.start();
        try {
            thread.join(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("最终结果: " + r);
    }
}
