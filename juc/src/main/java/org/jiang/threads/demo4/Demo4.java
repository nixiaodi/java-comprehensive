package org.jiang.threads.demo4;

public class Demo4 {
    static final Object lock = new Object();
    static int counter;

    public static void main(String[] args) {
        synchronized (lock) {
            counter++;
        }
    }
}
