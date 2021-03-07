package org.jiang.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class BlockingQueue<T> {
    // 任务队列
    private Deque<T> queue = new ArrayDeque<>();
    // 锁
    private ReentrantLock lock = new ReentrantLock();
    // 生产任务条件变量
    private Condition fullWaitSet = lock.newCondition();
    // 消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();
    // 容量
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // 阻塞获取任务
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T task = queue.removeFirst();
            fullWaitSet.signalAll();
            return task;
        } finally {
            lock.unlock();
        }
    }

    // 阻塞添加任务
    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() ==capacity) {
                try {
                    log.info("{}等待加入任务队列",element);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            log.info("加入任务队列:{}",element);
            emptyWaitSet.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // 阻塞添加任务
    public boolean add(T element,long timeout,TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(timeout);
            while (queue.size() ==capacity) {
                try {
                    log.info("{}等待加入任务队列",element);
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            log.info("加入任务队列:{}",element);
            emptyWaitSet.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }


    // 指定超时时间获取任务
    public T poll(long timeout, TimeUnit unit) {
        lock.lock();
        try {
            // 统一时间管理
            long nanos = unit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    if (nanos < 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T task = queue.removeFirst();
            fullWaitSet.signalAll();
            return task;
        } finally {
            lock.unlock();
        }
    }

    // 添加任务，但当阻塞队列满时执行拒绝策略
    public void tryPut(RejectPolicy<T> rejectPolicy,T element) {
        lock.lock();
        try {
            if (queue.size() == capacity) {
                rejectPolicy.reject(this,element);
            } else {
                queue.addLast(element);
                log.info("加入任务队列:{}",element);
                emptyWaitSet.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    // 获取当前任务队列大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
