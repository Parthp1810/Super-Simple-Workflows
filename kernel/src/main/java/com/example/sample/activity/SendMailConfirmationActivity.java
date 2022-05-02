package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class    SendMailConfirmationActivity implements Activity {
    @Override
    public String getId() {
        return Activity.super.getId();
    }

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Document check : RECEIVED");

        System.out.println("Sent confirmation email to user for document received");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }

}
