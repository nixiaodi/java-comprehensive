package org.jiang.threads.demo2;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class ThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("新建线程...");
            }
        };

        Thread thread2 = new Thread(() -> {
            System.out.println("新建Runnable线程");
        });

        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            log.info("hello");
            return 100;
        });

        Thread thread3 = new Thread(futureTask);
        thread3.start();

        Integer res = futureTask.get();

        log.info(String.valueOf(res));
    }
}
