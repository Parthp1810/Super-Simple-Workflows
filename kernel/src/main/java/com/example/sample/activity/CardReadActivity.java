package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;

public class CardReadActivity implements Activity {

    @Override
    public Info execute(Context activityContext) {

        System.out.println("Card Inserted: COMPLETED");
        activityContext.insert("Card-No", "****-****-****-1234");
        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
