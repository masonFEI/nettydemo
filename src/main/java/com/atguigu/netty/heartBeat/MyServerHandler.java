package com.atguigu.netty.heartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * MyServerHandler
 *
 * @author johnny.fei
 * @version 1.0.0
 * @since 2023-12-21 22:55
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent event) {
            String eventType = switch (event.state()) {
                case READER_IDLE -> "读空闲";
                case WRITER_IDLE -> "写空闲";
                case ALL_IDLE -> "读写空闲";
            };
            System.out.println(ctx.channel().remoteAddress() + "--超时时间--" + eventType);
            System.out.println("服务器做相应处理..");
        }
    }
}