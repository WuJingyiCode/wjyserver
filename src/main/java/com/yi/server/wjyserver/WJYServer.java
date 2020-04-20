package com.yi.server.wjyserver;

import com.yi.server.wjyserver.command.AvoidRepeatStartCommand;
import com.yi.server.wjyserver.command.ListCommand;
import com.yi.server.wjyserver.command.LogoCommand;
import com.yi.server.wjyserver.command.WJYServerConfig;
import com.yi.server.wjyserver.extension.ExtensionManager;
import com.yi.server.wjyserver.logger.WJYServerLogger;

/**
 * @author wujingyi
 */
public class WJYServer {
    private WJYServerLogger wjyServerLogger;
    private LogoCommand logoCommand;
    private AvoidRepeatStartCommand avoidRepeatStartCommand;
    private WJYServerConfig serverConfig;
    private ExtensionManager extensionManager;

    public WJYServer() {
        this.wjyServerLogger = new WJYServerLogger();
        this.logoCommand = new LogoCommand();
        this.avoidRepeatStartCommand = new AvoidRepeatStartCommand();
        this.serverConfig = new WJYServerConfig();
        this.extensionManager = new ExtensionManager(this);
    }

    public void start() {
        ListCommand listCommand = new ListCommand();
        listCommand.addCommand(wjyServerLogger);
        listCommand.addCommand(logoCommand);
        listCommand.addCommand(avoidRepeatStartCommand);
        listCommand.addCommand(serverConfig);
        listCommand.addCommand(extensionManager);
        listCommand.execute();
    }

    public WJYServerConfig getServerConfig() {
        return serverConfig;
    }

    public ExtensionManager getExtensionManager() {
        return extensionManager;
    }
}
