package com.yi.server.wjyserver.logger;

import com.yi.server.wjyserver.WJYServer;
import org.junit.Test;

public class WJYServerLoggerTest {
    @Test
    public void testLogger() {
        WJYServerLogger.LOGGER.info("<WJYServerLoggerTest> info...");
        WJYServerLogger.LOGGER.debug("<WJYServerLoggerTest> debug...");
        WJYServerLogger.LOGGER.warn("<WJYServerLoggerTest> warn...");
        WJYServerLogger.LOGGER.error("<WJYServerLoggerTest> error...");
    }

    @Test
    public void testServer() {
        WJYServer server = new WJYServer();
        server.start();
    }

}