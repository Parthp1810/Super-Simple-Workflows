package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class PinEntryActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {
        System.out.println(activityContext.get("Card-No"));
        System.out.println("Entering PIN");
        System.out.println("PIN Entered");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
