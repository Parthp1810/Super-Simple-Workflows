package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class UserVerificationActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {

        System.out.println("User KYC: Know Your Customer profile: ACTIVE");

        activityContext.insert("kyc-status", "ACTIVE");

        System.out.println("User Verified");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
