package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.workflow.impl.ParallelWorkflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParallelWorkflowBuilder {

    private String id;
    private List<Activity> activityList;
    public ParallelWorkflowBuilder() {
        this.id = UUID.randomUUID().toString();
        this.activityList = new ArrayList<>();
    }
    public ParallelWorkflowBuilder execute(Activity activity) {
        this.activityList.add(activity);
        return this;
    }
    public ParallelWorkflowBuilder execute(List<Activity> activities) {
        this.activityList.addAll(activities);
        return this;
    }
    public ParallelWorkflowBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ParallelWorkflow build() {
            return new ParallelWorkflow(this.id, this.activityList);
        }
}
