package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.WJYServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * 用于接收TCP连接。
 *
 * @author wujingyi
 */
public class TCPChannelAcceptor extends Command {
    private WJYServer server;
    private ServerSocketChannel serverSocketChannel;
    private ServerSocket serverSocket;
    private Selector selector;
    private SessionManager sessionManager;
    private boolean isRunning;
    private Thread acceptorThread;

    public TCPChannelAcceptor(WJYServer server) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 监听54250端口
        serverSocketChannel.bind(new InetSocketAddress(54250));
        selector = Selector.open();
        this.server = server;
        sessionManager = server.getSessionManager();
        // todo 改用线程池
        acceptorThread = new Thread(new AcceptorRunnable(), "Thread-TCPChannelAcceptor");
    }

    @Override
    protected void executeOnce() throws IOException {
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // todo 如何定义running状态？
        while (isRunning) {
            acceptorThread.start();
        }
    }

    @Override
    protected void rollbackOnce() {

    }

    private class AcceptorRunnable implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    int count = selector.select();
                    if (count > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            sessionManager.addNewSocketChannel(socketChannel);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
