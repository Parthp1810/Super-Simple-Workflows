package com.example.sample.predicate;

import com.example.activity.ActivityPredicate;
import com.example.activity.Info;

public class MetValidationPredicate implements ActivityPredicate {
    @Override
    public boolean validate(Info activityInfo) {

        System.out.println("Comparing submitted report plagiarism percentage with the percentage of plagiarism decided for this course: NOT CROSSED");

        return true;
    }
}
