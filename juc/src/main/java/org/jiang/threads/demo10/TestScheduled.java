package org.jiang.threads.demo10;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestScheduled {
    public static void main(String[] args) {
        // 计算每周的时间差
        long period = TimeUnit.DAYS.toMillis(7);
        // 计算当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取周四时间
        LocalDateTime thursday = now.withHour(18).withMinute(0).withSecond(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        // 判断当前时间是否超过周四
        if (now.compareTo(thursday) > 0) {
            thursday = thursday.plusDays(7);
        }
        // 获取当前时间和周四间隔
        long initDelay = Duration.between(now, thursday).toMillis();

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        pool.scheduleAtFixedRate(() -> {
            log.info("开始执行任务...");
        },initDelay,period,TimeUnit.MILLISECONDS);
    }
}
