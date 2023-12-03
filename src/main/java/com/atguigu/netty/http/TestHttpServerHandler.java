package com.atguigu.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;


public class TestHttpServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("对应的channel=" + ctx.channel() + "pipeline=" + ctx.pipeline() + "通过pipeline获取channnel" + ctx.pipeline().channel());

        System.out.println("当前ctx的handler=" + ctx.handler());

        if (msg instanceof HttpRequest) {
            System.out.println("ctx类型=" + ctx.getClass());
            System.out.println("pipeliene hashcode=" + ctx.pipeline().hashCode() + "TestHttpServerHandler hash=" + this.hashCode());

            System.out.println("msg类型=" + msg.getClass());
            System.out.println("客户端爹日" + ctx.channel().remoteAddress());


            HttpRequest httpRequest = (HttpRequest) msg;

            URI uri = new URI(httpRequest.uri());

            if ("favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico,不做响应");
                return;
            }

            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(response);


        }


    }
}
