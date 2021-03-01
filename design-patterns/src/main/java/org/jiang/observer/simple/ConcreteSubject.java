package org.jiang.observer.simple;

import sun.security.provider.DSAPublicKeyImpl;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> observerList;

    public ConcreteSubject() {
        observerList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        if (!observerList.contains(o)){
            observerList.add(o);
        }
    }

    @Override
    public void unregisterObserver(Observer o) {
        if (observerList.contains(o)){
            observerList.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        observerList.stream().forEach(Observer::update);
    }
}
