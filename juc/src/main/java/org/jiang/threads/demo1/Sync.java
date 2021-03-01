package org.jiang.threads.demo1;


import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class Sync {
    public static final String FILE_NAME = "D:\\OneDrive\\文档\\Java笔记\\JUC.md";

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(FILE_NAME);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            // System.out.println(new String(bytes,0,len));
        }
        log.info("读取完成...");
        log.info("do some things");
    }
}
