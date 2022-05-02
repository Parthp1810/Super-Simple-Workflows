package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.activity.ActivityPredicate;
import com.example.statemachine.StateMachine;
import com.example.workflow.impl.ConditionalWorkflow;

public class ConditionalWorkflowBuilder {

    private String id;
    private Activity activity;
    private Activity successActivity;
    private Activity failureActivity;
    private ActivityPredicate predicate;
    private StateMachine stateMachine;

    public ConditionalWorkflowBuilder() {
    }

    public ConditionalWorkflowBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ConditionalWorkflowBuilder execute(Activity firstActivity) {
        this.activity = firstActivity;
        return this;
    }

    public ConditionalWorkflowBuilder when(ActivityPredicate predicate) {
        this.predicate = predicate;
        return this;
    }

    public ConditionalWorkflowBuilder then(Activity nextActivity) {
        this.successActivity = nextActivity;
        return this;
    }

    public ConditionalWorkflowBuilder orElse(Activity nextActivity) {
        this.failureActivity = nextActivity;
        return this;
    }

    public ConditionalWorkflowBuilder stateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
        return this;
    }

    public ConditionalWorkflow build() {
        return new ConditionalWorkflow(this.id, this.predicate, this.activity, this.successActivity,
                this.failureActivity);
    }
}
