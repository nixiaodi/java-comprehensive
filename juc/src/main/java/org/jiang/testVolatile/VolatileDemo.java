package org.jiang.testVolatile;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally value : " + myData.atomicInteger);
    }
}


class MyData {
    public AtomicInteger atomicInteger = new AtomicInteger();


    public void addPlus() {
        atomicInteger.getAndIncrement();
    }
}