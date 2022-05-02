package com.example.sample.action;

import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;
import com.example.statemachine.Action;
import com.example.statemachine.impl.DefaultTrigger;

public class AllocationAction implements Action<DefaultTrigger> {
    @Override
    public Info execute(Context context, DefaultTrigger trigger) {
        System.out.println("Allocation is in progress");

        System.out.println(context.get("payment-workflow"));

        System.out.println("Allocation is completed");
        System.out.println("Current Balance :"+context.get("Balance"));
        return new DefaultInfo(Status.SUCCESS, context);
    }
}
