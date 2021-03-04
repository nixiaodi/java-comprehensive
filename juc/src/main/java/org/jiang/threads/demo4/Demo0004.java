package org.jiang.threads.demo4;

import java.util.concurrent.locks.ReentrantLock;

public class Demo0004 {
    private static int i;

    public synchronized static void main(String[] args) {
        i++;
    }

    new ReentrantLock
}
