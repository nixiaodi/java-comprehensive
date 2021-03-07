package org.jiang.flyweight.integerTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntegerTest {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(127);
        Integer b = 127;

        Integer c = Integer.valueOf(128);
        Integer d = 128;

        log.info("{}", a == b);
        log.info("{}",c == d);
    }
}
