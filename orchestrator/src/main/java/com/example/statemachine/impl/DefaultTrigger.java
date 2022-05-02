package com.example.statemachine.impl;

import com.example.statemachine.Trigger;

import java.util.UUID;

public class DefaultTrigger implements Trigger {

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
