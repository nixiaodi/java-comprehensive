package org.jiang.nio.client;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@Slf4j
public class NioClient {
    private Selector selector;

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.init("127.0.0.1",8000);
        client.connect();
    }

    private void connect() throws IOException {
        // 轮询
        while (true) {
            selector.select();
            // 获得selector中选中的项的迭代器
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                // 删除已选的key,以防重复处理
                it.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                        channel.configureBlocking(false);

                        //在这里给服务端发送信息
                        ByteBuffer buffer = ByteBuffer.wrap("HelloServer".getBytes());
                        channel.write(buffer);
                        //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限
                        // 获得了可读的事件
                        channel.register(selector,SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        read(key);
                    }
                }
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        //和服务端的read方法一样
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = channel.read(buffer);
        if (len != -1) {
            log.info("客户端收到信息:" + new String(buffer.array(), 0, len));
        }
    }

    private void init(String addr, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        // 设置非阻塞
        channel.configureBlocking(false);
        // 获取一个选择器
        this.selector = Selector.open();

        // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
        //用channel.finishConnect() 才能完成连接
        channel.connect(new InetSocketAddress(addr,port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }


}
