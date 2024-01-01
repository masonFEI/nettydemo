package com.atguigu.netty.inboudhandlerandoutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 加入一个出站的编码器
        pipeline.addLast(new MyLongToByteEncoder());
        // 加入一个入站的解码器
        pipeline.addLast(new MyByteToLongDecoder());
        // 处理逻辑
        pipeline.addLast(new MyClientHandler());
    }
}
