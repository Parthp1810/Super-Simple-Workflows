package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;
import com.example.annotations.StockPile;

public class NotifyUserRegCheckDocFormatActivity implements Activity {

    @Override
    public String getId() {
        return Activity.super.getId();
    }

    @Override
    @StockPile
    public Info execute(Context activityContext) {

        System.out.println("Uploaded assignment document type is PDF : FALSE");

        System.out.println("Sent email notification to the user that the sent assignment is not accepted due to document type failure");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
