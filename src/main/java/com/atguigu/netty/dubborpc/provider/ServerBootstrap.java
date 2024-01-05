package com.atguigu.netty.dubborpc.provider;

import com.atguigu.netty.dubborpc.netty.NettyServer;

public class ServerBootstrap {


    public static void main(String[] args) throws Exception {
        NettyServer.startServer("127.0.0.1", 7000);
    }


}
