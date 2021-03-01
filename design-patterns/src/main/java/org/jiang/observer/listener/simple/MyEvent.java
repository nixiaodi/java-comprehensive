package org.jiang.observer.listener.simple;

import org.springframework.context.ApplicationEvent;

import javax.xml.transform.Source;

public class MyEvent extends ApplicationEvent {

    private String message;

    public MyEvent(Object source,String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
