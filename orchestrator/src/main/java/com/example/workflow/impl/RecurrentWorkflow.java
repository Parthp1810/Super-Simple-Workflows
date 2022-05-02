package com.example.workflow.impl;

import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Activity;


public class RecurrentWorkflow extends DefaultWorkflow {

    private Activity activity;
    private Integer count;

    public RecurrentWorkflow(String id, Activity activity, Integer count) {
        super(id);
        this.activity = activity;
        this.count = count;
    }

    public RecurrentWorkflow(String id) {
        super(id);
    }

    @Override
    public Info execute(Context activityContext) {
        Info activityInfo = null;
        for (int i = 0; i < count; i++) {
            activityInfo = activity.execute(activityContext);
        }
        return activityInfo;
    }
}
