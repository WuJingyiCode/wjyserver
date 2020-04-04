package com.yi.server.wjyserver.logger;

import org.junit.Test;

public class WJYServerLoggerTest {
    @Test
    public void testLogger() {
        WJYServerLogger.LOGGER.info("<WJYServerLoggerTest> info...");
        WJYServerLogger.LOGGER.debug("<WJYServerLoggerTest> debug...");
        WJYServerLogger.LOGGER.warn("<WJYServerLoggerTest> warn...");
        WJYServerLogger.LOGGER.error("<WJYServerLoggerTest> error...");
    }
}