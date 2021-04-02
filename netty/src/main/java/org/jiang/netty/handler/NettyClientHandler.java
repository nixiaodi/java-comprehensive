package org.jiang.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 客户端 pipeline 中的 handler
 * @author 蒋小胖
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接服务器完成调用该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 1; i <= 5; i++) {
            //ByteBuf byteBuf = Unpooled.copiedBuffer("Msg No" + i + StringUtil.LINE_FEED, Charset.forName("utf-8"));

            String message = "Msg No" + i;
            ByteBuf buffer = Unpooled.buffer(1024);
            byte[] bytes = message.getBytes(CharsetUtil.UTF_8);
            //设置长度域的值，为有效数据的长度
            buffer.writeInt(bytes.length);
            //设置有效数据
            buffer.writeBytes(bytes);

            ctx.writeAndFlush(buffer);
        }
    }

    /**
     * 当通道有读取事件时会触发，即服务端发送数据给客户端
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("收到服务端的消息:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务端的地址：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
