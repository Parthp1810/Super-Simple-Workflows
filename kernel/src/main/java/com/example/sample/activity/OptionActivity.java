package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class OptionActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Options :");
        System.out.println("1) Withdraw");
        System.out.println("2) Check Balance");
        System.out.println("3) Exit");
        activityContext.insert("Balance", "****");
        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
