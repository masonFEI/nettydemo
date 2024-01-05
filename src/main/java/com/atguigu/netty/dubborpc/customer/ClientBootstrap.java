package com.atguigu.netty.dubborpc.customer;

import com.atguigu.netty.dubborpc.netty.NettyClient;
import com.atguigu.netty.dubborpc.publicinterface.HelloService;

public class ClientBootstrap {


    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws Exception {
        NettyClient customer = new NettyClient();

        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for (; ; ) {
            Thread.sleep(2 * 1000);
            String res = service.hello("你好，dubbo~");
            System.out.println("调用的结果 res=" + res);
        }

    }


}
