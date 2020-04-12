package com.yi.server.wjyserver.extension;

import com.yi.server.wjyserver.annotation.RequestListener;
import com.yi.server.wjyserver.logger.WJYServerLogger;

public class TestExtension {
    private static final String TEST_CMD = "0_0";

    @RequestListener(TEST_CMD)
    public void testExtension() {
        WJYServerLogger.LOGGER.info("<TestExtension> testExtension.");
    }
}
