package org.jiang.netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.LineSeparator;
import io.netty.util.CharsetUtil;
import org.jiang.netty.handler.NettyClientHandler;

/**
 * 客户端的channel初始化器
 * @author 蒋小胖
 */
public class CustomerClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //数据包最大长度是1024
        //长度域的起始索引是0
        //长度域的数据长度是4
        //矫正值为0，因为长度域只有 有效数据的长度的值
        //丢弃数据起始值是4，因为长度域长度为4，我要把长度域丢弃，才能得到有效数据
        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024,0,4,0,4));
        //添加编码器，使用默认的符号\n，字符集是UTF-8
        //ch.pipeline().addLast(new LineEncoder(LineSeparator.DEFAULT, CharsetUtil.UTF_8));
        ch.pipeline().addLast(new NettyClientHandler());
    }
}
