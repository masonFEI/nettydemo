package com.atguigu.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result;
    private String para;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive 被调用");
        context = ctx;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead 被调用");
        result = msg.toString();
        notify();//唤醒等待的线程
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常消息=" + cause.getMessage());
        ctx.close();
    }


    // 被代理对象调用，发送数据给服务器->wait->等待被唤醒->返回结果
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call1 被调用 ");

        context.writeAndFlush(para);
        wait();

        System.out.println("call2 被调用 ");
        return result;

    }

    void setPara(String para) {
        System.out.println("setPara");
        this.para = para;
    }


}
