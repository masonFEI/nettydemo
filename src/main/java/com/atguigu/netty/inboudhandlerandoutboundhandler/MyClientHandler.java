package com.atguigu.netty.inboudhandlerandoutboundhandler;

import com.atguigu.netty.codec2.MyDataInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    public void channelRead0(ChannelHandlerContext ctx,Long msg) throws Exception {
        System.out.println("服务器的地址：" + ctx.channel().remoteAddress());
        System.out.println("服务器回复的消息：" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
        ctx.writeAndFlush(123456l);
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
