package org.jiang.threads.demo7;

import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Demo007 {
    private static SpinLock lock = new SpinLock();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
        lock.lock();
        System.out.println(new Date());
        System.out.println("已经获取到锁");
    }
}
