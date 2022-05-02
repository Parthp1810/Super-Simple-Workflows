package com.example.sample.predicate;

import com.example.activity.ActivityPredicate;
import com.example.activity.Info;

public class OptionPredicate implements ActivityPredicate {
    @Override
    public boolean validate(Info activityInfo) {
        System.out.println("----------");
        System.out.println("Option: Withdraw");
        return true;
    }
}
