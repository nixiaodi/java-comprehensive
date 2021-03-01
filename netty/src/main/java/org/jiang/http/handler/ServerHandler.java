package org.jiang.http.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class ServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断msg是否是http request请求
        if (msg instanceof HttpRequest) {
            System.out.println("msg 类型: " + msg.getClass());
            System.out.println("客户端地址: " + ctx.channel().remoteAddress());

            // 向浏览器回复信息
            ByteBuf buf = Unpooled.copiedBuffer("hello 我是服务器", CharsetUtil.UTF_8);

            // 构建http response，即http响应
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.ACCEPT_CHARSET,"utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,buf.readableBytes());

            // 将构建好的响应返回
            ctx.writeAndFlush(response);
        }
    }
}
