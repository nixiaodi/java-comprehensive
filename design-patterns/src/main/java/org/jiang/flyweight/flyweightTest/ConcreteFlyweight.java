package org.jiang.flyweight.flyweightTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteFlyweight implements Flyweight{
    private String key;

    public ConcreteFlyweight(String key) {
        this.key = key;
        log.info("具体享元{}被创建",key);
    }

    @Override
    public void operation(UnsharedConcreteFlyweight outState) {
        log.info("具体享元{}被调用",key);
        log.info("非享元信息是:{}",outState.getInfo());
    }
}
