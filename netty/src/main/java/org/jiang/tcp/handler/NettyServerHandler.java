package org.jiang.tcp.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //System.out.println("服务器读取线程: " + Thread.currentThread().getName());
        //System.out.println("server ctx = " + ctx);
        //// 将消息转换为ByteBuf
        //ByteBuf buf = (ByteBuf) msg;
        //
        //System.out.println("客户端发送的消息: " + buf.toString(CharsetUtil.UTF_8));
        //System.out.println("客户端地址: " + ctx.channel().remoteAddress());
        //TimeUnit.SECONDS.sleep(10);
        //ctx.writeAndFlush(Unpooled.copiedBuffer("处于阻塞的消息",CharsetUtil.UTF_8));
        //ctx.channel().eventLoop().execute(() -> {
        //    try {
        //        TimeUnit.SECONDS.sleep(10);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //    ctx.writeAndFlush(Unpooled.copiedBuffer("处于阻塞的消息",CharsetUtil.UTF_8));
        //    ctx.writeAndFlush(Unpooled.copiedBuffer(new Date().toString(),CharsetUtil.UTF_8));
        //
        //});

        ctx.channel().eventLoop().schedule(() -> {
            //try {
            //    TimeUnit.SECONDS.sleep(10);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            ctx.writeAndFlush(Unpooled.copiedBuffer("处于阻塞的消息2",CharsetUtil.UTF_8));
            ctx.writeAndFlush(Unpooled.copiedBuffer(new Date().toString(),CharsetUtil.UTF_8));
        },5,TimeUnit.SECONDS);

        System.out.println("go on...");
        ctx.writeAndFlush(Unpooled.copiedBuffer(new Date().toString(),CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
