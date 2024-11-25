package com.github.xinuwiz.sql.provider.util;

import java.lang.reflect.InvocationTargetException;

public final class ReflectionUtil {

    public static <T> T newInstanceWithoutException(Class<T> clazz, String errorMessage) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
