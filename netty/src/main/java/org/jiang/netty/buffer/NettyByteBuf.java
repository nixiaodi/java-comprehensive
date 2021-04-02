package org.jiang.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyByteBuf {
    public static void main(String[] args) {
        // 创建byteBuf对象，该对象内部包含一个字节数组byte[10]
        // 通过readerIndex和writerIndex和capacity，将buffer分成三个区域
        // 已经读取的区域：[0,readerIndex)
        // 可读取的区域：[readerIndex,writerIndex)
        // 可写的区域: [writerIndex,capacity)
        ByteBuf buffer = Unpooled.buffer(10);
        log.info("byteBuf:{}",buffer);

        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        log.info("byteBuf:{}",buffer);

        for (int i = 0; i < 5; i++) {
            log.info("{}",buffer.writeByte(i));
        }
        log.info("byteBuf:{}",buffer);

        for (int i = 0; i < 5; i++) {
            log.info("{}",buffer.writeByte(i));
        }
        log.info("byteBuf:{}",buffer);

        //用Unpooled工具类创建ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello".getBytes(CharsetUtil.UTF_8));
        //使用相关的方法
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            log.info(new String(array,CharsetUtil.UTF_8));
            log.info("byteBuf:{}",byteBuf);
            //可读的字节数  12
            log.info("{}",byteBuf.readableBytes());
            log.info("{}",byteBuf.readerIndex());
            log.info("{}",byteBuf.writerIndex());
            log.info("{}",byteBuf.capacity());
            // 获取数组index=0的字符h的ascii码，h=104
            log.info("{}",byteBuf.getByte(0));
        }

        //使用for取出各个字节
        for (int i = 0; i < byteBuf.readableBytes(); i++) {
            log.info("{}",(char) byteBuf.getByte(i));
        }

        //范围读取
        log.info("{}",byteBuf.getCharSequence(0, 5, CharsetUtil.UTF_8));
    }
}
