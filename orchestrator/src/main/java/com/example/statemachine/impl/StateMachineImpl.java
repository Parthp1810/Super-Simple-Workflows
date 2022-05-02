package com.example.statemachine.impl;

import com.example.activity.Context;
import com.example.statemachine.Definition;
import com.example.statemachine.State;
import com.example.statemachine.StateMachine;
import com.example.statemachine.Trigger;

import java.util.ArrayList;
import java.util.List;

public class StateMachineImpl implements StateMachine {

    private State present;

    private List<State> states;

    private State from;

    private List<State> to = new ArrayList<>();

    private List<Definition> definitions = new ArrayList<>();

    public StateMachineImpl(State from) {
        this.from = from;
        present = from;
    }

    public State execute(Context context, Trigger trigger) {
        for (Definition definition: definitions) {
            definition.getAction().execute(context, trigger);
            this.present = definition.getTo();
        }

        return present;
    }

    public void add(Definition definition) {
        definitions.add(definition);
    }

    public void add(State state) {
        to.add(state);
    }

    public State getPresent() {
        return present;
    }

    public void setPresent(State present) {
        this.present = present;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public List<State> getTo() {
        return to;
    }

    public void setTo(List<State> to) {
        this.to = to;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }
}
