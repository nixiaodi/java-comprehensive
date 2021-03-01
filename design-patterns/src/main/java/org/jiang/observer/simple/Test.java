package org.jiang.observer.simple;

public class Test {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver observerOne = new ConcreteObserver(subject,"observerOne");
        ConcreteObserver observerTwo = new ConcreteObserver(subject,"observerTwo");
        subject.unregisterObserver(observerTwo);
        subject.notifyObserver();
    }
}
