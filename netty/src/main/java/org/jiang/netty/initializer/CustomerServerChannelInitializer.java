package org.jiang.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.LineSeparator;
import io.netty.util.CharsetUtil;
import org.jiang.netty.handler.NettyServerHandler;

/**
 * Channel initializer
 * 处理到来的socketChannel
 * @author 蒋小胖
 */
public class CustomerServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //解码器需要设置数据的最大长度，我这里设置成1024
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        ch.pipeline().addLast(new NettyServerHandler());
    }
}
