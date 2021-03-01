package org.jiang.observer.simple;

public class ConcreteObserver implements Observer{
    private String name;
    private Subject subject;

    public ConcreteObserver(Subject subject,String name) {
        this.subject = subject;
        this.name = name;
        subject.registerObserver(this);
    }

    @Override
    public void update() {
        // 具体实现
        System.out.println(name + "状态更新");
    }
}
