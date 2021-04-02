package org.jiang.bio.client;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);

        String str = "服务端你好,这是我发送的数据";

        socket.getOutputStream().write(str.getBytes("UTF-8"));
        socket.getOutputStream().flush();
        log.info("发送数据结束");

        byte[] allocate = new byte[10];

        int read = socket.getInputStream().read(allocate);
        log.info(new String(allocate,0,read));
    }
}
