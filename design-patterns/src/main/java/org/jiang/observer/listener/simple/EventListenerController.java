package org.jiang.observer.listener.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventListenerController {
    @Autowired
    private MyEventPublisher publisher;

    @GetMapping("/publish/{message}")
    public void publish(@PathVariable String message) {
        publisher.publishEvent(message);
    }
}
