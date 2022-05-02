package com.example.activity.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.statemachine.StateMachine;
import com.example.statemachine.Trigger;
import java.util.UUID;

public abstract class StateMachineActivity implements Activity {
    private final StateMachine stateMachine;
    private final Trigger trigger;

    protected StateMachineActivity(StateMachine stateMachine, Trigger trigger) {
        this.stateMachine = stateMachine;
        this.trigger = trigger;
    }

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Info execute(Context context) {
        stateMachine.execute(context, trigger);
        return null;
    }
}
