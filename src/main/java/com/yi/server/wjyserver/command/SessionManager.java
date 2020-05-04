package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.WJYServer;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wujingyi
 */
public class SessionManager extends Command {

    private WJYServer server;
    private List<SocketChannel> newSocketChannelList;

    public SessionManager(WJYServer server) {
        this.server = server;
        newSocketChannelList = new ArrayList<>();
    }

    @Override
    protected void executeOnce() throws Throwable {

    }

    @Override
    protected void rollbackOnce() {

    }

    public void addNewSocketChannel(SocketChannel socketChannel) {
        newSocketChannelList.add(socketChannel);
    }

    public List<SocketChannel> getNewSocketChannelListAndClear() {
        List<SocketChannel> socketChannelList = new ArrayList<>(newSocketChannelList);
        newSocketChannelList.clear();
        return socketChannelList;
    }
}
