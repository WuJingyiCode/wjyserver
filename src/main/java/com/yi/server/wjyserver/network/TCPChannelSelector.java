package com.yi.server.wjyserver.network;

import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.command.Command;
import com.yi.server.wjyserver.constant.NetworkConst;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Set;

/**
 * @author wujingyi
 */
public class TCPChannelSelector extends Command {
    private WJYServer server;
    private Selector selector;
    private SessionManager sessionManager;
    /**
     * todo 如何判定线程状态
     */
    private boolean isRunning;
    private Thread selectorThread;

    public TCPChannelSelector(WJYServer server) {
        this.server = server;
        sessionManager = server.getSessionManager();
        selectorThread = new Thread(new SelectorRunnable(), "Thread-ChannelSelector");
    }

    @Override
    protected void executeOnce() throws Throwable {
        selector = Selector.open();
        isRunning = true;
        selectorThread.start();
    }

    @Override
    protected void rollbackOnce() {

    }

    private class SelectorRunnable implements Runnable {
        @Override
        public void run() {
            try {
                while (isRunning) {
                    List<SocketChannel> socketChannelList = sessionManager.getNewSocketChannelListAndClear();
                    if (!socketChannelList.isEmpty()) {
                        for (SocketChannel channel : socketChannelList) {
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    for (SelectionKey selectionKey : selectionKeys) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        // todo specify ByteBuffer size.
                        ByteBuffer byteBuffer = ByteBuffer.allocate(24);
                        // read byte from channel into buffer.
                        socketChannel.read(byteBuffer);
                        // todo 处理拆包，粘包问题
                        int packageLength = byteBuffer.getInt();
                        //todo 如何跳出循环
                        while (true) {
                            int msgType = byteBuffer.get();
                            if (byteBuffer.remaining() >= packageLength) {
                                RequestHandler requestHandler = RequestHandler.getTestRequestHandler();
                                requestHandler.handleRequest(byteBuffer);
                            } else {
                                break;
                            }
                        }
                        // todo sendBack
                    }
                }
            } catch (IOException e) {
                // todo log
            }
        }
    }
}
