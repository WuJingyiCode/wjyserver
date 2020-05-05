package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.WJYServer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author wujingyi
 */
public class TCPChannelSelector extends Command {
    private WJYServer server;
    private Selector selector;
    private SessionManager sessionManager;
    /** todo 如何判定线程状态 */
    private boolean isRunning;
    private Thread selectorThread;

    public TCPChannelSelector(WJYServer server) throws IOException {
        this.server = server;
        sessionManager = server.getSessionManager();
        selector = Selector.open();
        selectorThread = new Thread(new SelectorRunnable(), "Thread-ChannelSelector");
    }

    @Override
    protected void executeOnce() throws Throwable {
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
                        for (SocketChannel channel: socketChannelList) {
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                    }
                    int count = selector.select();
                    if (count > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            // todo specify ByteBuffer size.
                            ByteBuffer byteBuffer = ByteBuffer.allocate(24);
                            // read byte from channel into buffer.
                            socketChannel.read(byteBuffer);
                            // todo 处理拆包，粘包问题
                        }
                    }
                }
            } catch (IOException e) {
                // todo log
            }
        }
    }
}
