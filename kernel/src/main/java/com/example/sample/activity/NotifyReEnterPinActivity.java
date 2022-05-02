package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class NotifyReEnterPinActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Please Reenter Correct Pin!");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
