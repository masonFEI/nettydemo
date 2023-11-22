package com.atguigu.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {


    public static void main(String[] args) throws Exception {

        // 创建serverSocketChannel->server
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 得到1个selector对象
        Selector selector = Selector.open();

        // 绑定一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel 注册到selector ,关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等到客户端连接
        while (true) {

            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            // 如果返回的>0，就获取到相关的selectorKey集合
            // 1.如果返回的>0，表示已经很u哦渠道关注的事件
            // 2.selector.selectedKeys() 返回关注事件的集合
            // 通过selectionKeys 反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {// 如果是OP_ACCEPT,有新的客户端连接
                    // 该客户端生成一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    System.out.println("客户端连接成功，生成了一个socketChannel" + socketChannel.hashCode());

                    // 将socketChannel注册到seletor,同时给socketChannel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }

                if (key.isReadable()) {
                    // 通过key，反向获取到对应channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端" + new String(buffer.array()));
                }
                //手动移除当前selectionKey
                keyIterator.remove();
            }


        }


    }


}
