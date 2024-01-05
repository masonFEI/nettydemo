package com.atguigu.netty.dubborpc.publicinterface;


import org.springframework.stereotype.Repository;

@Repository
public interface HelloService {


    String hello(String mes);

}
