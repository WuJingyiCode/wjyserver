package com.yi.server.wjyserver.extension;

import com.yi.server.wjyserver.RequestType;
import com.yi.server.wjyserver.logger.WJYServerLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wujingyi
 */
public class RequestDispatcher {
    private RequestType focusRequestType;
    private Map<String, RequestListener> cmd2ListenerMap = new HashMap<>();

    public RequestDispatcher(RequestType focusRequestType) {
        this.focusRequestType = focusRequestType;
    }

    public void addListeners(Extension extension) {
        Class<?> clazz = extension.getClass();
        Method[] allMethods = clazz.getMethods();
        for (Method method : allMethods) {
            Annotation annotation = method.getAnnotation(focusRequestType.getAnnotationClazz());
            if (annotation == null) {
                continue;
            }
            if (!isCompatible(method.getParameterTypes(), focusRequestType.getMethodParamClazzArr())) {
                WJYServerLogger.LOGGER.warn(String.format("<RequestDispatcher> parameters type not match. method = %s", method.getName()));
                continue;
            }
            try {
                String cmd = (String) annotation.getClass().getMethod("value").invoke(annotation);
                RequestListener listener = focusRequestType.createRequestListener(cmd, extension, method);
                cmd2ListenerMap.put(cmd, listener);
            } catch (Throwable t) {
                WJYServerLogger.LOGGER.error("<RequestDispatcher> addListeners fail.", t);
            }
        }
    }

    private boolean isCompatible(Class<?>[] methodClazzArr, Class<?>[] requireClazzArr) {
        if (methodClazzArr == requireClazzArr) {
            return true;
        }
        int length = methodClazzArr.length;
        if (length != requireClazzArr.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!methodClazzArr[i].equals(requireClazzArr[i])) {
                return false;
            }
        }
        return true;
    }
}
