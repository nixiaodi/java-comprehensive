package org.jiang.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.jiang.netty.initializer.CustomerClientChannelInitializer;

@Slf4j
public class NettyClient {
    public static void main(String[] args) {
        // 客户端事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            //创建客户端启动对象
            //注意客户端使用的不是ServerBootstrap而是Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    // 使用NioSocketChannel作为客户端的通道实现
                    .channel(NioSocketChannel.class)
                    .handler(new CustomerClientChannelInitializer());

            log.info("Netty Client Startup...");
            //启动客户端去连接服务器端
            ChannelFuture future = bootstrap.connect("127.0.0.1", 9000).sync();
            //对通道关闭进行监听
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
