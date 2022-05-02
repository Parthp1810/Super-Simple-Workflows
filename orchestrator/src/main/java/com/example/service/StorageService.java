package com.example.service;

import java.util.List;

public interface StorageService {

    Boolean save(List<Object> requests, Object response, String identifier);
}
