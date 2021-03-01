package org.jiang.http.utils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.jiang.http.handler.ServerHandler;

public class CustomerServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();

        // 加入一个netty提供的httpServerCodec codec => [code,decode]
        // HttpServerCodec()是netty提供的基于http的编解码器
        pipeline.addLast("CustomerHttpServerCodec",new HttpServerCodec());
        // 添加自定义的handler
        pipeline.addLast("CustomerServerHandler",new ServerHandler());
    }
}
