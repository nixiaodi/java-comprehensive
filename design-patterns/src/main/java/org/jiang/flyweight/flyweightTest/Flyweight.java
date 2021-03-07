package org.jiang.flyweight.flyweightTest;

public interface Flyweight {
    /**
     * 注入非享元角色的外部状态
     */
    void operation(UnsharedConcreteFlyweight outState);
}
