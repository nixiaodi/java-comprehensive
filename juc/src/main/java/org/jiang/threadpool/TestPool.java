package org.jiang.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestPool {
    public static void main(String[] args) {
        ThreadPoll pool = new ThreadPoll(2, 1000, TimeUnit.MILLISECONDS, 5,(queue,task) -> {
            log.info("队列已满,放弃任务{}",task);

        });
        for (int i = 0; i < 10; i++) {
            int j = i;
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}",j);
            });
        }
    }
}
