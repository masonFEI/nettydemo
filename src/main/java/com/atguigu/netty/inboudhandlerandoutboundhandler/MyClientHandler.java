package com.atguigu.netty.inboudhandlerandoutboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    public void channelRead0(ChannelHandlerContext ctx,Long msg) throws Exception {
        System.out.println("服务器的地址：" + ctx.channel().remoteAddress());
        System.out.println("服务器回复的消息：" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
//        ctx.writeAndFlush(123456l);
        // abcdabcdabcd是十六个字符
        // 这两个类型并不匹配，也就不会走编码器
        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcd",CharsetUtil.UTF_8));
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
