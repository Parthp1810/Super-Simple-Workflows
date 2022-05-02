package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class BalanceVerificationActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Check Account: ACTIVE");
        System.out.println("Check enough balance for withdraw: VERIFIED");
        System.out.println("Check enough balance into ATM: VERIFIED");
        System.out.println("Current Balance :"+activityContext.get("Balance"));
        System.out.println("Balance Verified");
        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
