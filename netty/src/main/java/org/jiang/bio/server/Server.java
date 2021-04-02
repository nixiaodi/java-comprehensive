package org.jiang.bio.server;



import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.*;

@Slf4j
public class Server {
    TimeUnit unit;
    BlockingQueue workQueue;
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(5,
            10,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            (r,executors) -> {
                log.info("放弃执行{}任务",r);
            });

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);

        while (true) {
            log.info("等待客户端连接...");
            Socket socket = serverSocket.accept();
            log.info("连接建立...");

            EXECUTOR.execute(() -> {
                try {
                    handler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    public static void handler(Socket socket) throws IOException {
        log.info("Thread name:" + Thread.currentThread().getName());
        byte[] allocate = new byte[10];
        log.info("准备读取数据...");
        int read = 0;
        read = socket.getInputStream().read(allocate);
        log.info(new String(allocate,0,read));
        socket.getOutputStream().write("数据读取完毕...".getBytes("UTF-8"));
        socket.getOutputStream().flush();

        log.info("数据发送完毕...");
    }
}
