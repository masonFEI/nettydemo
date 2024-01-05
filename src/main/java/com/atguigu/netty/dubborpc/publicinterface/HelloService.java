package com.atguigu.netty.dubborpc.publicinterface;


import org.springframework.stereotype.Repository;


// 接口，服务提供方与消费方都需要
@Repository
public interface HelloService {


    String hello(String mes);

}
