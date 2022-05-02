package com.example.statemachine;

public interface Definition {

    String getId();

    State getFrom();

    State getTo();

    Class<? extends Trigger> getTrigger();

    Action getAction();
}
