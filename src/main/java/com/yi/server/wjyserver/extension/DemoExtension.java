package com.yi.server.wjyserver.extension;

import com.yi.server.wjyserver.annotation.ProtobufRequestListener;
import message.ProtobufMessage;

/**
 * @author wujingyi
 */
public class DemoExtension extends Extension {
    private static final String CMD_TEST = "";

    public DemoExtension(int id) {
        super(id);
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @ProtobufRequestListener(CMD_TEST)
    public void test() {
        ProtobufMessage message = ProtobufMessage.create();
        message.putInt("", 1);
    }
}
