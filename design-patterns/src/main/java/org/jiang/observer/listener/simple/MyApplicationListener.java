package org.jiang.observer.listener.simple;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
public class MyApplicationListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("非注解监听器: " + event.getMessage());
    }
}
