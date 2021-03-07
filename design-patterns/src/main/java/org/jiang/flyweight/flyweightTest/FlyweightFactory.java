package org.jiang.flyweight.flyweightTest;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class FlyweightFactory {
    private HashMap<String,Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = flyweights.get(key);
        if (flyweight != null) {
            log.info("享元{}已存在，被成功获取",key);
        } else {
            flyweight = new ConcreteFlyweight(key);
            flyweights.put(key,flyweight);
        }
        return flyweight;
    }
}
