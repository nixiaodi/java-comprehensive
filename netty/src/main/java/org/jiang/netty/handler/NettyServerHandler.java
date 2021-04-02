package org.jiang.netty.handler;

import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * 自定义Handler需要继承netty规定好的某个HandlerAdapter(规范)
 * @author 蒋小胖
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private int count;

    /**
     * 读取客户端发送的数据
     * @param ctx channelPipeline中每个handler的上下文对象
     * @param msg 客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("服务器读取线程:{}",Thread.currentThread().getName());
        //将 msg 转成一个 ByteBuf，类似NIO 的 ByteBuffer
        ByteBuf buffer = (ByteBuf) msg;
        log.info("客户端发送的数据:{}",buffer.toString(CharsetUtil.UTF_8));
        // 打印接收的次数
        log.info("接收到的数据次数是:{}",(++this.count));
    }

    /**
     * 数据读取完毕处理方法
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ByteBuf buffer = Unpooled.copiedBuffer("Hello Client".getBytes(CharsetUtil.UTF_8));
        ctx.writeAndFlush(buffer);
    }

    /**
     * 处理异常, 一般是需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(cause.getMessage());
        ctx.close();
    }
}
