package com.atguigu.netty.dubborpc.netty;

import com.atguigu.netty.protocoltcp.MyMessageDecoder;
import com.atguigu.netty.protocoltcp.MyMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyMessageDecoder());
        pipeline.addLast(new MyMessageEncoder());
        // 自定义处理方法
        pipeline.addLast(new MyServerHandler());
    }
}
