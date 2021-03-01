package org.jiang.threads.demo1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class Async {
    public static final String FILE_NAME = "D:\\OneDrive\\文档\\Java笔记\\JUC.md";

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(FILE_NAME);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] bytes = new byte[1024];
            int len = 0;
            while (true) {
                try {
                    if (!((len = inputStream.read(bytes)) != -1)) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // System.out.println(new String(bytes,0,len));
            }
            log.info("读取完成...");
        },"t1").start();
        log.info("do some things");
    }
}
