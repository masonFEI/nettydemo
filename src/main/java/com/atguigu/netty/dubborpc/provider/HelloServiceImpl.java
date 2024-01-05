package com.atguigu.netty.dubborpc.provider;

import com.atguigu.netty.dubborpc.publicinterface.HelloService;
import org.springframework.stereotype.Service;


@Service
public class HelloServiceImpl implements HelloService {

    private static int count = 0;

    // 消费方调用
    @Override
    public String hello(String mes) {
        System.out.println("收到客户端消息=" + mes);
        System.out.println();

        if (mes != null) {
            return "你好客户端，我已经收到你的消息。消息为：【" + mes + "],第" + (++count) + "次\n";
        } else {
            return "你好客户端，我已经收到你的消息";
        }

    }
}
