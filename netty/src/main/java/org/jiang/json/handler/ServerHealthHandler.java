package org.jiang.json.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.jiang.json.model.CallbackBean;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/4
 * @Version 1.0
 */
@Slf4j
public class ServerHealthHandler extends ChannelInboundHandlerAdapter {

    private static final AsciiString CONTENT_TYPE = new AsciiString("Content-Type");
    private static final AsciiString CONTENT_LENGTH = new AsciiString("Content-Length");
    private static final AsciiString CONNECTION = new AsciiString("Connection");
    private static final AsciiString KEEP_ALIVE = new AsciiString("keep-alive");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            ObjectMapper mapper = new ObjectMapper();
            // 封装回调参数
            CallbackBean callback = null;
            try {
                callback = mapper.readValue(parseJsonRequest(request),CallbackBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            assert callback != null;
            log.info(callback.toString());
        }
    }


    private String parseJsonRequest(FullHttpRequest request) {
        ByteBuf jsonBuf = request.content();
        return jsonBuf.toString(CharsetUtil.UTF_8);
    }
}
