package org.jiang.threads.demo2;



public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("新建线程...");
            }
        };

        Thread thread2 = new Thread(() -> {
            System.out.println("新建Runnable线程");
        });
    }
}
