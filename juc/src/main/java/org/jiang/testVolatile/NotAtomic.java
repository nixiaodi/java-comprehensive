package org.jiang.testVolatile;

public class NotAtomic {
    volatile int num = 0;

    public synchronized void add() {
        ++num;
    }
}
