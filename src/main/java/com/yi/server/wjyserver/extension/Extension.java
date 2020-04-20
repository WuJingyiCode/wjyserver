package com.yi.server.wjyserver.extension;

import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.command.Command;
import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.util.List;

/**
 * @author wujingyi
 */
public abstract class Extension extends Command {

    private WJYServer server;

    private int id;

    public Extension(int id) {
        this.id = id;
    }

    /**
     * 初始化Extension
     */
    public abstract void init();

    /**
     * 初始化Extension
     */
    public abstract void destroy();

    @Override
    protected void executeOnce() {
        WJYServerLogger.LOGGER.info(String.format("<Extension> init %s", this.getClass().getName()));
    }

    @Override
    protected void rollbackOnce() {
        WJYServerLogger.LOGGER.info(String.format("<Extension> destroy %s", this.getClass().getName()));
    }

    public void setWJYServer(WJYServer server) {
        this.server = server;
        addListeners();
    }

    public void addListeners() {
        this.server.getExtensionManager().addListeners(this);
    }
}
