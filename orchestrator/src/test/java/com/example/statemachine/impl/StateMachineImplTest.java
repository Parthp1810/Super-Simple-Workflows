package com.example.statemachine.impl;

import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.impl.DefaultContext;
import com.example.statemachine.*;
import com.example.statemachine.builder.DefinitionBuilder;
import com.example.statemachine.builder.MachineBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StateMachineImplTest {

    @Test
    public void definitionAdditionTest() {
        State startState = new State("STATE_ONE");
        State endState = new State("STATE_TWO");
        FirstAction firstAction = new FirstAction();

        Definition definition = new DefinitionBuilder()
                .id("DEFINITION_ONE")
                .from(startState)
                .to(endState)
                .trigger(FirstTrigger.class)
                .action(firstAction)
                .build();
        List<Definition> definitions = Collections.singletonList(definition);

        StateMachine stateMachine = new MachineBuilder(startState)
                .definitions(definitions)
                .states(Arrays.asList(startState, endState))
                .to(Collections.singletonList(endState))
                .build();

        Assert.assertEquals(definitions, stateMachine.getDefinitions());
    }

    @Test
    public void stateMachineStateChangeTest() {
        State startState = new State("STATE_ONE");
        State endState = new State("STATE_TWO");

        FirstTrigger firstTrigger = new FirstTrigger();
        FirstAction firstAction = new FirstAction();
        Definition definition = new DefinitionBuilder()
                .id("DEFINITION_ONE")
                .from(startState)
                .to(endState)
                .trigger(FirstTrigger.class)
                .action(firstAction)
                .build();

        StateMachineImpl stateMachine = new StateMachineImpl(startState);
        stateMachine.setStates(Arrays.asList(startState, endState));
        stateMachine.setDefinitions(Arrays.asList(definition));
        stateMachine.setTo(Arrays.asList(endState));

        State finalState = stateMachine.execute(new DefaultContext(), firstTrigger);
        Assert.assertEquals(endState, finalState);
    }

    public static class FirstTrigger implements Trigger {

        @Override
        public String getId() {
            return "FIRST_TRIGGER";
        }
    }

    public static class FirstAction implements Action<FirstTrigger> {

        @Override
        public Info execute(Context context, FirstTrigger trigger) {
            System.out.println(trigger.getId());
            return null;
        }
    }
}
