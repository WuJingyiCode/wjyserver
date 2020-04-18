package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.WJYServer;
import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wujingyi
 */
public class AvoidRepeatStartCommand extends Command {

    @Override
    protected void executeOnce() {
        WJYServerLogger.LOGGER.info("<AvoidRepeatStartCommand> executeOnce");
    }

    @Override
    protected void rollbackOnce() {
        WJYServerLogger.LOGGER.info("<AvoidRepeatStartCommand> rollbackOnce");
    }
}
