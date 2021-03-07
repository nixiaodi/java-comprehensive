package org.jiang.threads.demo7;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class Demo7 {
    private static Person person = new Person("jiang", 18);
    private static AtomicReference<Person> reference = new AtomicReference<>(person);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            reference.get().setName("liu");
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Person newPerson = new Person("nidi", 23);
        boolean flag = reference.compareAndSet(person, newPerson);
        log.info(String.valueOf(flag));
        log.info(reference.get().toString());
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private String name;
    private int age;
}
