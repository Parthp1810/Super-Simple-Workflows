package com.example.statemachine.impl;

import com.example.statemachine.Action;
import com.example.statemachine.State;
import com.example.statemachine.Trigger;
import com.example.statemachine.Definition;

public class DefinitionImpl<T extends Trigger> implements Definition {

    private String id;

    private State from;

    private State to;

    private Class<T> trigger;

    private Action<T> action;

    public DefinitionImpl() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public Class<T> getTrigger() {
        return trigger;
    }

    public void setTrigger(Class<T> trigger) {
        this.trigger = trigger;
    }

    public Action<T> getAction() {
        return action;
    }

    public void setAction(Action<T> action) {
        this.action = action;
    }
}
