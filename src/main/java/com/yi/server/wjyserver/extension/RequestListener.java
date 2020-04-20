package com.yi.server.wjyserver.extension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wujingyi
 */
public class RequestListener {
    private String cmd;
    private Object instance;
    private Method method;

    public RequestListener(String cmd, Object instance, Method method) {
        this.cmd = cmd;
        this.instance = instance;
        this.method = method;
    }

    public void invoke() throws InvocationTargetException, IllegalAccessException {
        method.invoke(instance);
    }
}
