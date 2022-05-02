package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class NotifyFailureActivity implements Activity {

    @Override
    public Info execute(Context activityContext)  {

        System.out.println("Not Enough Balance into Account");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
