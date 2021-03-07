package org.jiang.threads.demo7;

import sun.util.locale.provider.AuxLocaleProviderAdapter;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (!sign.compareAndSet(null, currentThread)) {

        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();
        while (!sign.compareAndSet(currentThread, null)) {

        }
    }
}
