package com.example.statemachine.impl;

import com.example.statemachine.Definition;
import com.example.statemachine.State;
import com.example.statemachine.Trigger;
import com.example.statemachine.builder.DefinitionBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class DefinitionImplTest {

    @Test
    public void definitionCreationTest() {
        State startState = new State("STATE_ONE");
        State endState = new State("STATE_TWO");

        Definition definitionOne = new DefinitionBuilder()
                .id(UUID.randomUUID().toString())
                .from(startState)
                .to(endState)
                .trigger(DefaultTrigger.class)
                .build();

        Assert.assertEquals(startState, definitionOne.getFrom());
        Assert.assertEquals(endState, definitionOne.getTo());
    }

    @Test
    public void definitionDifferentiationTest() {
        State startState = new State("STATE_ONE");
        State endState = new State("STATE_TWO");

        Definition definitionOne = new DefinitionBuilder()
                .id(UUID.randomUUID().toString())
                .from(startState)
                .to(endState)
                .trigger(DefaultTrigger.class)
                .build();

        Definition definitionTwo = new DefinitionBuilder()
                .id(UUID.randomUUID().toString())
                .from(startState)
                .to(endState)
                .trigger(DemoTrigger.class)
                .build();

        Assert.assertNotEquals(definitionOne, definitionTwo);

    }

    public static class DemoTrigger implements Trigger {

        @Override
        public String getId() {
            return "DEMO_TRIGGER";
        }
    }
}
