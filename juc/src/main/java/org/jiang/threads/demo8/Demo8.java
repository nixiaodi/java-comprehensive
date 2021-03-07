package org.jiang.threads.demo8;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Slf4j
public class Demo8 {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-dd");
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                TemporalAccessor accessor = format.parse("2021-03-04");
                log.info("{}",accessor);
            }).start();
        }
    }
}
