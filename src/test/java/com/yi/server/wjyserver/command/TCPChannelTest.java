package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.config.ServerSystemProperty;
import org.junit.Test;

import static org.junit.Assert.*;

public class TCPChannelTest {

    public void beforeStart() {
        System.setProperty(ServerSystemProperty.SERVER_CONFIG_PATH, "E:\\Study\\MyCode\\wjyserver\\src\\main\\resources");
        System.setProperty(ServerSystemProperty.SERVER_WORKSPACE_CONFIG_PATH, "E:\\Study\\MyCode\\wjyserver\\src\\main\\resources");
    }

    @Test
    public void test() {
        beforeStart();
        WJYServer server = new WJYServer();
        server.start();
    }

}