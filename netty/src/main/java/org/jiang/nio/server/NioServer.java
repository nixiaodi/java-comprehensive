package org.jiang.nio.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@Slf4j
public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8000));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            log.info("选择器开始监听...");
            // 阻塞
            selector.select();

            log.info("事件发生...");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //删除本次已处理的key，防止下次select重复处理
                iterator.remove();
                handle(key);
            }
        }
    }

    private static void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            log.info("连接事件发生...");
            // 这里的key是ServerSocketChannel的key
            ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
            // 这时并不会阻塞，因为在selector那里已经确定了连接事件
            SocketChannel channel = socketChannel.accept();
            channel.configureBlocking(false);
            channel.register(key.selector(),SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            log.info("客户端数据传输过来可读...");
            // 这里的key是ServerSocketChannel生成的SocketChannel的key
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 这里并不会阻塞，因为已经确定了读取事件，但是读这个过程属于同步
            int read = channel.read(buffer);
            if (read != -1) {
                log.info("从客户端读取到的数据:{}",new String(buffer.array(),0,read));
            }
            ByteBuffer bufferToWriter = ByteBuffer.wrap("hello client...".getBytes());
            channel.write(bufferToWriter);
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            log.info("write事件...");
            key.interestOps(SelectionKey.OP_READ);
        }
    }
}
