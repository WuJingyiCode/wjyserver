package com.yi.server.wjyserver;

import com.yi.server.wjyserver.command.*;
import com.yi.server.wjyserver.extension.ExtensionManager;
import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wujingyi
 */
public class WJYServer {
    private WJYServerLogger wjyServerLogger;
    private LogoCommand logoCommand;
    private AvoidRepeatStartCommand avoidRepeatStartCommand;
    private WJYServerConfig serverConfig;
    private ExtensionManager extensionManager;
    private SessionManager sessionManager;

    public WJYServer() {
        this.wjyServerLogger = new WJYServerLogger();
        this.logoCommand = new LogoCommand();
        this.avoidRepeatStartCommand = new AvoidRepeatStartCommand();
        this.serverConfig = new WJYServerConfig();
        this.extensionManager = new ExtensionManager(this);
        this.sessionManager = new SessionManager(this);
    }

    public void start() throws Throwable {
        ListCommand listCommand = new ListCommand();
        listCommand.addCommand(wjyServerLogger);
        listCommand.addCommand(logoCommand);
        listCommand.addCommand(avoidRepeatStartCommand);
        listCommand.addCommand(serverConfig);
        listCommand.addCommand(extensionManager);
        listCommand.addCommand(sessionManager);
        listCommand.execute();
    }

    public WJYServerConfig getServerConfig() {
        return serverConfig;
    }

    public ExtensionManager getExtensionManager() {
        return extensionManager;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
