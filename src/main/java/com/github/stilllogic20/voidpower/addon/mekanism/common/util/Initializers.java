package com.github.stilllogic20.voidpower.addon.mekanism.common.util;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Initializers {

    public static Initializer of(Class<?> type) {
        Callable<Void> callable = () -> null;

        Method[] methods = type.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(Construct.class) != null) {
                final Callable<Void> prev = callable;
                callable = () -> {
                    Void ret = prev.call();
                    boolean accessible = method.isAccessible();
                    method.setAccessible(true);
                    try {
                        method.invoke(null);
                    } finally {
                        method.setAccessible(accessible);
                    }
                    return ret;
                };
            }
        }

        return new OfCallable(callable);
    }

    public static final class OfCallable implements Initializer {

        private final Callable<?> callable;

        OfCallable(Callable<?> callable) {
            this.callable = callable;
        }

        @Override
        public Exception initialize() {
            try {
                callable.call();
            } catch (Exception exception) {
                return exception;
            }
            return null;
        }

    }

}
