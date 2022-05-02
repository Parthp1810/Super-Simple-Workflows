package com.example.sample.action;

import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;
import com.example.activity.impl.DefaultInfo;
import com.example.statemachine.Action;
import com.example.statemachine.impl.DefaultTrigger;


public class SendReportToAdminAction implements Action<DefaultTrigger> {
    @Override
    public Info execute(Context context, DefaultTrigger trigger) {
        System.out.println("Report validation is completed.");

        System.out.println("The plagiarism percentage is less than the default set value.");

        System.out.print("Plagiarism count is: ");

        System.out.println(context.get("plagiarism-workflow"));

        System.out.println("Hence, sent report in email to Admin.");

        return new DefaultInfo(Status.SUCCESS, context);
    }
}
