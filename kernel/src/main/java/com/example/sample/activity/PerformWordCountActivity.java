package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class PerformWordCountActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Wordcount is in progress");

        System.out.println("Counting words is completed");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
