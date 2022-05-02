package com.example.sample.predicate;

import com.example.activity.ActivityPredicate;
import com.example.activity.Info;

public class PaymentPredicate implements ActivityPredicate {
    @Override
    public boolean validate(Info activityInfo) {

        System.out.println("Check account balance: VALID");
        System.out.println("Contact bank for mandate approval: APPROVED");
        System.out.println("Check with AMC for Folio validation: VERIFIED");

        return true;
    }
}
