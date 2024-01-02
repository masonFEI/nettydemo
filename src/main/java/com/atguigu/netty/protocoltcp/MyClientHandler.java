package com.atguigu.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {


    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端发送5条数据
        for (int i = 0; i < 5; i++) {
            String mes = "今天天气冷，吃火锅";
            byte[] content = mes.getBytes(StandardCharsets.UTF_8);
            int length = mes.getBytes(StandardCharsets.UTF_8).length;

            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLen(length);
            messageProtocol.setContent(content);
            ctx.writeAndFlush(messageProtocol);
        }
    }


    @Override
    public void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("客户端接收到消息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, StandardCharsets.UTF_8));
        System.out.println("客户端接收到消息数量=" + (++this.count));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常消息=" + cause.getMessage());
        ctx.close();
    }


}
