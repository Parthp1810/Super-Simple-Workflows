package com.example.activity.impl;

import com.example.activity.Context;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultContext implements Context {

    private final Map<String, Object> context = new ConcurrentHashMap<>();

    public void insert(String key, Object value) {
        context.put(key, value);
    }

    public Object get(String key) {
        return context.get(key);
    }

    public Set<Map.Entry<String, Object>> get() {
        return context.entrySet();
    }
}
