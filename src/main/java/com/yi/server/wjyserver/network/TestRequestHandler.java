package com.yi.server.wjyserver.network;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TestRequestHandler extends RequestHandler {
    @Override
    public void handleRequest(ByteBuffer byteBuffer) {
        List<Long> resultList = new ArrayList<>();
        while (byteBuffer.remaining() > 0) {
            resultList.add(byteBuffer.getLong());
        }
    }
}
