package com.yi.server.wjyserver.command;

import com.yi.server.wjyserver.logger.WJYServerLogger;
import org.junit.Test;

public class CommandTest {

    @Test
    public void testExecuteAndRollback() {
        Command testCommand = new Command() {
            @Override
            protected void executeOnce() {
                WJYServerLogger.LOGGER.info("execute");
            }

            @Override
            protected void rollbackOnce() {
                WJYServerLogger.LOGGER.info("rollbackOnce");
            }
        };

        try {
            testCommand.execute();
            throw new RuntimeException("Test rollback.");
        } catch (Throwable t) {
            testCommand.rollback();
        }
    }

}
