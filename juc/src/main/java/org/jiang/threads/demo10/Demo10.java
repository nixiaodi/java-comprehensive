package org.jiang.threads.demo10;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@Slf4j
public class Demo10 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<Boolean> future = pool.submit(() -> {
            log.info("task1");
            int i = 1 / 0;
            return true;
        });

        log.info("debug:{}",future.get());
    }

    private static void method02() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.scheduleWithFixedDelay(() -> {
            try {
                log.info("task1");
                int i = 1 /0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        },1,1, TimeUnit.SECONDS);

        //pool.schedule(() -> {
        //    log.info("task1");
        //},1,TimeUnit.SECONDS);
    }

    private static void method01() {
        Timer timer = new Timer();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                log.info("task 1");
                int i = 1 / 0;
            }
        };

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.info("task 2");
            }
        };

        timer.schedule(task1,1000);
        timer.schedule(task2,1000);
    }
}
