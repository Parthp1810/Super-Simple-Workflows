package com.example.statemachine;


import com.example.activity.Context;

import java.util.List;

public interface StateMachine {

    State execute(Context context, Trigger trigger);

    void add(Definition definition);

    List<Definition> getDefinitions();
}
