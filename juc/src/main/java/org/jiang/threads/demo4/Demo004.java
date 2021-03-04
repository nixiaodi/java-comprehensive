package org.jiang.threads.demo4;

public class Demo004 {
    static final Object lock = new Object();

    public static void method01() {
        synchronized (lock) {
            // 同步块

        }
    }

    public static void method02() {
        synchronized (lock) {
            // 同步块
        }
    }

    public static void method03() {
        synchronized (lock) {
            // 同步块
        }
    }
}
