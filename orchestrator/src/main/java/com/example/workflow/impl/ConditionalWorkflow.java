package com.example.workflow.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.ActivityPredicate;


public class ConditionalWorkflow extends DefaultWorkflow {

    private final Activity activity;
    private final Activity successActivity;
    private final Activity failureActivity;
    private final ActivityPredicate predicate;

    public ConditionalWorkflow(String id, ActivityPredicate predicate, Activity activity, Activity successActivity,
                               Activity failureActivity) {
        super(id);
        this.activity = activity;
        this.successActivity = successActivity;
        this.failureActivity = failureActivity;
        this.predicate = predicate;
    }

    @Override
    public Info execute(Context activityContext) {
        Info activityInfo = activity.execute(activityContext);

        if (predicate.validate(activityInfo)) {
            activityInfo = successActivity.execute(activityContext);
        } else {
            activityInfo = failureActivity.execute(activityContext);
        }
        return activityInfo;
    }
}
