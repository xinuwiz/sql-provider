package com.github.xinuwiz.sql.provider;

import com.github.xinuwiz.sql.provider.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;

public class EntityAdapterManager {

    private final Map<Class<?>, EntityAdapter<?>> map;

    public EntityAdapterManager() {
        this.map = new HashMap<>();
    }

    public <T> void register(Class<? extends EntityAdapter<T>> clazz) {
        EntityAdapter<?> instance = ReflectionUtil.newInstanceWithoutException(clazz, "Failed to create entity adapter.");
        map.put(clazz, instance);
    }

    public <T> EntityAdapter<T> get(Class<? extends EntityAdapter<T>> clazz) {
        return (EntityAdapter<T>) map.get(clazz);
    }
}
