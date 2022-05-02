package com.example.activity.impl;

import com.example.activity.Context;
import com.example.statemachine.State;
import com.example.statemachine.StateMachine;
import com.example.statemachine.Trigger;
import com.example.statemachine.impl.DefaultTrigger;
import com.example.statemachine.impl.StateMachineImpl;
import org.junit.Assert;
import org.junit.Test;

public class DefaultStateMachineActivityTest {

    @Test
    public void stateMachineExecuteTest() {
        StateMachine stateMachine = new StateMachineImpl(new State("STATE_ONE"));
        Trigger trigger = new DefaultTrigger();
        Context context = new DefaultContext();
        StateMachineActivity defaultStateMachineActivity = new DefaultStateMachineActivity(stateMachine, trigger);
        defaultStateMachineActivity.execute(context);

        Assert.assertNotNull(defaultStateMachineActivity);
    }
}
