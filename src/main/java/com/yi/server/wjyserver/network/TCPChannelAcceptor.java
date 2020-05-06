package com.yi.server.wjyserver.network;

import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.command.Command;
import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.util.Set;

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
    // todo 如何定义running状态？
    private boolean isRunning;
    private Thread acceptorThread;

    public TCPChannelAcceptor(WJYServer server) {
        this.server = server;
        sessionManager = server.getSessionManager();
        // todo 改用线程池
        acceptorThread = new Thread(new AcceptorRunnable(), "Thread-TCPChannelAcceptor");
        acceptorThread.setDaemon(false);
    }

    @Override
    protected void executeOnce() throws IOException, InterruptedException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 监听54250端口
        serverSocketChannel.bind(new InetSocketAddress(54250));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        isRunning = true;
        acceptorThread.start();
    }

    @Override
    protected void rollbackOnce() {

    }

    private class AcceptorRunnable implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                try {
                    WJYServerLogger.LOGGER.info("<TCPChannelAcceptor> running");
                    selector.selectNow();
                    WJYServerLogger.LOGGER.info("<TCPChannelAcceptor> after select");
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    for (SelectionKey selectionKey : selectionKeys) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        sessionManager.addNewSocketChannel(socketChannel);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            WJYServerLogger.LOGGER.info("<TCPChannelAcceptor> out of running");
        }
    }
}
