package com.yi.server.wjyserver.network;

import java.nio.channels.SocketChannel;

/**
 * @author wujingyi
 */
public class Session {
    private SocketChannel socketChannel;

    public Session(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
