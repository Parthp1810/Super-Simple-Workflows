package com.example.statemachine.builder;

import com.example.statemachine.Definition;
import com.example.statemachine.State;
import com.example.statemachine.StateMachine;
import com.example.statemachine.impl.StateMachineImpl;

import java.util.List;

public class MachineBuilder {

    private final StateMachineImpl stateMachine;

    public MachineBuilder(State from) {
        stateMachine = new StateMachineImpl(from);
    }

    public MachineBuilder definition(Definition definition) {
        stateMachine.add(definition);
        return this;
    }

    public MachineBuilder definitions(List<Definition> definitions) {
        for (Definition definition: definitions) {
            stateMachine.add(definition);
        }
        return this;
    }

    public MachineBuilder states(List<State> states) {
        for (State state: states) {
            stateMachine.add(state);
        }
        return this;
    }

    public MachineBuilder from(State state) {
        stateMachine.setFrom(state);
        return this;
    }

    public MachineBuilder to(List<State> state) {
        stateMachine.setTo(state);
        return this;
    }

    public StateMachine build() {
        return stateMachine;
    }
}
