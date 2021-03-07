package org.jiang.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoll {
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;
    // 线程集合
    private HashSet<Worker> workers = new HashSet<>();
    // 核心线程数
    private int coreSize;
    // 获取任务的超时时间
    private long timeout;
    // 时间单位
    private TimeUnit unit;
    // 拒绝策略
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPoll(int coreSize, long timeout, TimeUnit unit, int queueCapacity,RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        this.taskQueue = new BlockingQueue(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    // 执行任务
    public void execute(Runnable task) {
        // 当任务数没有超过核心线程数时，直接交给worker线程处理
        // 如果任务数超过核心线程数，就放入阻塞队列中
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.info("新增worker:{},task:{}",worker,task);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.tryPut(rejectPolicy,task);
            }
        }
    }

    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // 如果任务不为空就直接执行
            // 当任务执行完毕不能销毁线程，而是从任务队列中继续获取任务
            while (task != null || (task = taskQueue.poll(timeout,unit)) != null) {
                try {
                    log.info("正在执行{}...",task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.info("移除worker:{}",this);
                workers.remove(this);
            }
        }
    }
}


