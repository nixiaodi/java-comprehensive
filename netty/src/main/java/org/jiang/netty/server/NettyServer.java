package org.jiang.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.jiang.netty.initializer.CustomerServerChannelInitializer;

@Slf4j
public class NettyServer {
    public static void main(String[] args) {
        //创建两个线程组bossGroup和workerGroup, 含有的子线程NioEventLoop的个数默认为cpu核数的两倍
        // bossGroup只是处理连接请求 ,真正的和客户端业务处理，会交给workerGroup完成
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 服务器的启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup)
                    // 使用ServerSocketChannel作为服务器通道的实现
                    .channel(NioServerSocketChannel.class)
                    // 多个客户端请求到来，服务端不能处理的请求在队列中等待
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new CustomerServerChannelInitializer());

            log.info("Netty Server Startup...");

            //绑定一个端口并且同步, 生成了一个ChannelFuture异步对象，通过isDone()等方法可以判断异步事件的执行情况
            //启动服务器(并绑定端口)，bind是异步操作，sync方法是等待异步操作执行完毕
            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
            // 向future对象注册监听器,监听事件
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info("服务单监听9000端口启动成功...");
                } else {
                    log.info("服务端启动失败");
                }
            });

            //对通道关闭进行监听，closeFuture是异步操作，监听通道关闭
            // 通过sync方法同步等待通道关闭处理完毕，这里会阻塞等待通道关闭完成
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }


    }
}
