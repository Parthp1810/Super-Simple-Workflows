package com.example.sample.predicate;

import com.example.activity.ActivityPredicate;
import com.example.activity.Info;

public class PinVerificationPredicate implements ActivityPredicate {
    @Override
    public boolean validate(Info activityInfo) {

        System.out.println("Check Card Status: Active");

        System.out.println("PIN Approval: APPROVED");

        System.out.println("PIN verified");

        return true;
    }
}
