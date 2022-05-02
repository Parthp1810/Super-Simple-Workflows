package com.example.usecase.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;
import com.example.annotations.Item;
import com.example.annotations.StockPile;

public class NotificationActivity implements Activity {
    @Override
    public String getId() {
        return Activity.super.getId();
    }

    @Override
    public Info execute(Context activityContext) {
        System.out.println("Notify Success to user");

        activityContext.insert("e89b-42d3-a135", "notification-context");

        System.out.println("Notified Success to user!");

        return new DefaultInfo(Status.SUCCESS, activityContext);
    }
}
