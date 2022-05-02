package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.activity.ActivityPredicate;
import com.example.workflow.impl.RecurrentWorkflow;


public class RecurrentWorkflowBuilder {

    private Activity activity;
    private Integer count;
    private String id;

    public RecurrentWorkflowBuilder() {
    }

    public RecurrentWorkflowBuilder execute(Activity activity) {
        this.activity = activity;
        return this;
    }

    public RecurrentWorkflowBuilder count(Integer count) {
        this.count = count;
        return this;
    }

    public RecurrentWorkflowBuilder id(String id)
    {
        this.id = id;
        return this;
    }

    public RecurrentWorkflow build() {
        return new RecurrentWorkflow(this.id, this.activity, this.count);
    }

}
