package org.jiang.observer.listener.simple;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class MyEventListener {
    @EventListener
    public void listenerOne(MyEvent event) {
        System.out.println("注解监听器One: " + event.getMessage());
        System.out.println(new Date());
    }

    @EventListener
    @Async
    public void listenerTwo(MyEvent event) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("注解监听器Two: " + event.getMessage());
        System.out.println(new Date());
    }
}
