package com.yi.server.wjyserver;

import com.yi.server.wjyserver.command.AvoidRepeatStartCommand;
import com.yi.server.wjyserver.command.ListCommand;
import com.yi.server.wjyserver.command.LogoCommand;
import com.yi.server.wjyserver.command.WJYServerConfig;
import com.yi.server.wjyserver.logger.WJYServerLogger;

/**
 * @author wujingyi
 */
public class WJYServer {
    private WJYServerLogger wjyServerLogger;
    private LogoCommand logoCommand;
    private AvoidRepeatStartCommand avoidRepeatStartCommand;
    private WJYServerConfig serverConfig;

    public WJYServer() {
        this.wjyServerLogger = new WJYServerLogger();
        this.logoCommand = new LogoCommand();
        this.avoidRepeatStartCommand = new AvoidRepeatStartCommand();
    }

    public void start() {
        ListCommand listCommand = new ListCommand();
        listCommand.addCommand(wjyServerLogger);
        listCommand.addCommand(logoCommand);
        listCommand.addCommand(avoidRepeatStartCommand);
    }

    public WJYServerConfig getServerConfig() {
        return serverConfig;
    }
}
