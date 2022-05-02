package com.example.statemachine.builder;

import com.example.statemachine.Action;
import com.example.statemachine.Definition;
import com.example.statemachine.impl.DefinitionImpl;
import com.example.statemachine.State;
import com.example.statemachine.Trigger;

public class DefinitionBuilder {

    private final DefinitionImpl definition;

    public DefinitionBuilder() {
        definition = new DefinitionImpl();
    }

    public DefinitionBuilder id(String id) {
        definition.setId(id);
        return this;
    }

    public DefinitionBuilder from(State state) {
        definition.setFrom(state);
        return this;
    }

    public DefinitionBuilder to(State state) {
        definition.setTo(state);
        return this;
    }

    public DefinitionBuilder trigger(Class<? extends Trigger> trigger) {
        definition.setTrigger(trigger);
        return this;
    }

    public DefinitionBuilder action(Action<? extends Trigger> action) {
        definition.setAction(action);
        return this;
    }

    public Definition build() {
        return definition;
    }
}
