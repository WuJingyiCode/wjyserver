package com.yi.server.wjyserver.extension;

import com.yi.server.wjyserver.annotation.RequestListener;
import com.yi.server.wjyserver.logger.WJYServerLogger;

/**
 * @author wujingyi
 */
public class TestExtension extends Extension {
    private static final String TEST_CMD = "0_0";

    public TestExtension(int id) {
        super(id);
    }

    @RequestListener(TEST_CMD)
    public void testExtension() {
        WJYServerLogger.LOGGER.info("<TestExtension> testExtension.");
    }
}
