package com.example.activity;

import java.util.Map;
import java.util.Set;

public interface Context {

    void insert(String key, Object value);

    Object get(String key);

    Set<Map.Entry<String, Object>> get();
}
