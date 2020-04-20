package com.yi.server.wjyserver;

import com.yi.server.wjyserver.annotation.ProtobufRequestListener;
import com.yi.server.wjyserver.extension.RequestListener;
import message.Message;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wujingyi
 */
public enum RequestType {
    /**
     *
     */
    PROTOBUF(ProtobufRequestListener.class, new Class[]{String.class, Message.class}) {
        @Override
        public RequestListener createRequestListener(String cmd, Object instance, Method method) {
            return new RequestListener(cmd, instance, method);
        }
    },
    ;

    private Class<? extends Annotation> annotationClazz;
    private Class<?>[] methodParamClazzArr;

    RequestType(Class<? extends Annotation> annotationClazz, Class<?>[] methodParamClazzArr) {
        this.annotationClazz = annotationClazz;
        this.methodParamClazzArr = methodParamClazzArr;
    }

    public Class<? extends Annotation> getAnnotationClazz() {
        return annotationClazz;
    }

    public Class<?>[] getMethodParamClazzArr() {
        return methodParamClazzArr;
    }

    public abstract RequestListener createRequestListener(String cmd, Object instance, Method method);
}
