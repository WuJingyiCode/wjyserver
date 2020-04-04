package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.logger.WJYServerLogger;

public class LogoCommand extends Command {

    @Override
    protected void executeOnce() {
        WJYServerLogger.LOGGER.info("Hello. WJYServer!");
    }

    @Override
    protected void rollbackOnce() {
        WJYServerLogger.LOGGER.info("Goodbye. WJYServer!");
    }
}
