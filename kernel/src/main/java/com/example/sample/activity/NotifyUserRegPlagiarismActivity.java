package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;
import com.example.annotations.StockPile;

public class NotifyUserRegPlagiarismActivity implements Activity {

    @Override
    public String getId() {
        return Activity.super.getId();
    }

    @Override
    @StockPile
    public Info execute(Context activityContext) {

        System.out.println("Plagiarism percentage is more than the default");

        System.out.println("Sent email notification to the user that the sent assignment is not accepted");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
