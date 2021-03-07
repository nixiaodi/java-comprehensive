package org.jiang.threads.demo9;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class Demo9 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        List<Future<Object>> futureList = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    TimeUnit.SECONDS.sleep(1);
                    return 1;
                },
                () -> {
                    log.debug("begin");
                    TimeUnit.SECONDS.sleep(2);
                    return 2;
                },
                () -> {
                    log.debug("begin");
                    TimeUnit.SECONDS.sleep(3);
                    return 3;
                }
        ));

        futureList.forEach(f -> {
            try {
                log.info("{}",f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
