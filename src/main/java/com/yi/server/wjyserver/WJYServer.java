package com.yi.server.wjyserver;

import com.yi.server.wjyserver.command.*;
import com.yi.server.wjyserver.extension.ExtensionManager;
import com.yi.server.wjyserver.logger.WJYServerLogger;
import com.yi.server.wjyserver.network.SessionManager;
import com.yi.server.wjyserver.network.TCPChannelAcceptor;
import com.yi.server.wjyserver.network.TCPChannelSelector;

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
    private TCPChannelAcceptor channelAcceptor;
    private TCPChannelSelector channelSelector;

    public WJYServer() {
        this.wjyServerLogger = new WJYServerLogger();
        this.logoCommand = new LogoCommand();
        this.avoidRepeatStartCommand = new AvoidRepeatStartCommand();
        this.serverConfig = new WJYServerConfig();
        this.extensionManager = new ExtensionManager(this);
        this.sessionManager = new SessionManager(this);
        this.channelAcceptor = new TCPChannelAcceptor(this);
        this.channelSelector = new TCPChannelSelector(this);
    }

    public void start() {
        ListCommand listCommand = new ListCommand();
        listCommand.addCommand(wjyServerLogger);
        listCommand.addCommand(logoCommand);
        listCommand.addCommand(avoidRepeatStartCommand);
        listCommand.addCommand(serverConfig);
        listCommand.addCommand(extensionManager);
        listCommand.addCommand(sessionManager);
        listCommand.addCommand(channelAcceptor);
        listCommand.addCommand(channelSelector);
        try {
            listCommand.execute();
        } catch (Throwable t) {
            WJYServerLogger.LOGGER.error("<WJYServer> start fail! ", t);
            listCommand.rollback();
        }
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
