package org.jiang.observer.simple;
import org.jiang.observer.simple.Observer;

public interface Subject {
    /**
     * 注册观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 取消注册观察者
     * @param o
     */
    void unregisterObserver(Observer o);

    /**
     * 通知观察者
     */
    void notifyObserver();
}
