package com.example.usecase.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class PaymentGatewayActivity implements Activity{
    @Override
    public Info execute(Context activityContext) {

        System.out.println("Payment is in progress");

        activityContext.insert("e89b-42d3-a456", "payment-context");

        System.out.println("Payment is completed");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}


