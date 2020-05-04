package com.yi.server.wjyserver.command;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.ByteBuffer;


public class MyByteBuffer {
    private IoBuffer buffer;

    public MyByteBuffer(int capacity) {
        this(capacity, false);
    }

    public MyByteBuffer(int capacity, boolean useDirectBuffer) {
        this(IoBuffer.allocate(capacity, useDirectBuffer));
    }

//	public MyByteBuffer(MyByteBuffer byteBuffer, int length) {
//		this(IoBuffer.wrap(byteBuffer.array(), byteBuffer.position(), length));
//	}

    public MyByteBuffer(byte[] byteArray) {
        this(IoBuffer.wrap(byteArray));
    }

    private MyByteBuffer(IoBuffer _buffer) {
        this.buffer = _buffer;
    }
}
