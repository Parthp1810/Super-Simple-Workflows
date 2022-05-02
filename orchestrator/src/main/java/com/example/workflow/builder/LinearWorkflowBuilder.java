package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.workflow.impl.LinearWorkflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LinearWorkflowBuilder {

    private String id;
    private List<Activity> activityList;

    public LinearWorkflowBuilder() {
        this.id = UUID.randomUUID().toString();
        this.activityList = new ArrayList<>();
    }

    public LinearWorkflowBuilder id(String id) {
        this.id = id;
        return this;
    }

    public LinearWorkflowBuilder execute(Activity activity) {
        this.activityList.add(activity);
        return this;
    }

    public LinearWorkflowBuilder execute(List<Activity> firstActivities) {
        this.activityList.addAll(firstActivities);
        return this;
    }

    public LinearWorkflowBuilder then(Activity activity) {
        this.activityList.add(activity);
        return this;
    }

    public LinearWorkflowBuilder then(List<Activity> activities) {
        this.activityList.addAll(activities);
        return this;
    }

    public LinearWorkflow build() {
        return new LinearWorkflow(this.id, this.activityList);
    }
}
