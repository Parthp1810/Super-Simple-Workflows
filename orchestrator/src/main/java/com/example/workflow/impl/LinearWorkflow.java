package com.example.workflow.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LinearWorkflow extends DefaultWorkflow {

    private final List<Activity> activities = new ArrayList<>();

    public LinearWorkflow(String id, List<Activity> activities) {
        super(id);
        this.activities.addAll(activities);
    }

    @Override
    public Info execute(Context activityContext) {
        Info activityInfo = null;
        for (Activity activity : activities) {
            activityInfo = activity.execute(activityContext);
            if (activityInfo != null && Status.FAILURE.equals(activityInfo.getStatus())) {
                break;
            }
        }
        return activityInfo;
    }
}
