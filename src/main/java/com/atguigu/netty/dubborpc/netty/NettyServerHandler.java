package com.atguigu.netty.dubborpc.netty;

import com.atguigu.netty.dubborpc.customer.ClientBootstrap;
import com.atguigu.netty.dubborpc.publicinterface.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;


public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    @Autowired
    private HelloService helloService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("--服务端开始收到来自客户端的消息--");
        System.out.println("原始消息：" + msg);

        if (msg.toString().startsWith(ClientBootstrap.providerName)) {
            String result = helloService.hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
