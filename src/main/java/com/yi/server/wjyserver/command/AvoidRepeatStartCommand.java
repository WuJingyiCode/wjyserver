package com.yi.server.wjyserver.command;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wujingyi
 */
public class AvoidRepeatStartCommand extends Command {

    @Override
    protected void executeOnce() {
        Logger.getAnonymousLogger().log(Level.INFO, "Hello. Avoid!");
    }

    @Override
    protected void rollbackOnce() {
        Logger.getAnonymousLogger().log(Level.INFO, "Goodbye. Avoid!");
    }
}
