package com.atguigu.nio;

import lombok.var;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {


    public static void main(String[] args) {


        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            buffer.put((byte) i);
        }
        buffer.flip();
        var readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }


        readOnlyBuffer.put((byte) 100);





    }


}
