package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class CheckPlagiarismPercentageActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Plagiarism check is in progress");

        activityContext.insert("plagiarism-workflow", "12%");

        System.out.println("Completed plagiarism check");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }

}
