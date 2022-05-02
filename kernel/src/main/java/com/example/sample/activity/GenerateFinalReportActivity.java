package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class GenerateFinalReportActivity implements Activity {
@Override
    public String getId() {
        return Activity.super.getId();
    }

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Final report is getting generated with word count and plagiarism percentage.");

        System.out.println("Final report got generated");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
