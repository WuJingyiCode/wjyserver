package com.yi.server.wjyserver.logger;


import com.yi.server.wjyserver.command.Command;
import org.apache.log4j.Logger;

public class WJYServerLogger extends Command {
    public static final Logger LOGGER = Logger.getLogger(WJYServerLogger.class);

    private boolean ensureLoggerInit() {
        return WJYServerLogger.LOGGER.isDebugEnabled();
    }

    @Override
    protected void executeOnce() {
        if (ensureLoggerInit()) {
            return;
        }
        throw new RuntimeException("<WJYServerLogger> init WJYServerLogger failed.");
    }

    @Override
    protected void rollbackOnce() {

    }
}
