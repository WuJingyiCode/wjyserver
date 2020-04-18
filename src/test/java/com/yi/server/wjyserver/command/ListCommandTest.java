package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.config.ExtensionConfig;
import com.yi.server.wjyserver.config.ServerSystemProperty;
import com.yi.server.wjyserver.extension.ExtensionManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ListCommandTest {
    @Test
    public void testListCommand() {
        ListCommand listCommand = new ListCommand();
        listCommand.addCommand(new LogoCommand());
        listCommand.addCommand(new AvoidRepeatStartCommand());
        try {
            listCommand.execute();
            throw new RuntimeException("Test rollback");
        } catch (Throwable t) {
            listCommand.rollback();
        }
    }

    public void init() {
        System.setProperty(ServerSystemProperty.SERVER_CONFIG_PATH, "G:\\code\\repo_yi\\wjyserver\\src\\main\\resources");
        System.setProperty(ServerSystemProperty.SERVER_WORKSPACE_CONFIG_PATH, "G:\\code\\repo_yi\\wjyserver\\src\\main\\resources");
    }

    public void destroy() {

    }

    @Test
    public void testConfigCommand() {
        init();
        ListCommand listCommand = new ListCommand();
        WJYServerConfig config = new WJYServerConfig();
        listCommand.addCommand(config);
        listCommand.execute();
        List<ExtensionConfig> list = config.getExtensionConfigList();
        Assert.assertEquals(2, list.size());
        destroy();
    }

    @Test
    public void testExtensionManager() {
        init();
        WJYServer wjyServer = new WJYServer();
        wjyServer.start();
        destroy();
    }
}