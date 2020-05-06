package com.yi.server.wjyserver.network;

import java.nio.ByteBuffer;

public abstract class RequestHandler {

    public abstract void handleRequest(ByteBuffer byteBuffer);

    public static RequestHandler getTestRequestHandler() {
        return new TestRequestHandler();
    }
}
