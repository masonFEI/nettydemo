package com.atguigu.netty.inboudhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     *  decode 会根据接收到的数据，被调用多次，指导确定没有新的元素被田间到list
     * @param ctx           上下文对象      the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in            入站的buteBuf  the {@link ByteBuf} from which to read data
     * @param out           list集合       the {@link List} to which decoded messages should be added
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecoder 被调用");
        // 因为long8个字节，才能读取
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }

    }
}
