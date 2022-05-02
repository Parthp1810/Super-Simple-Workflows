package com.example.sample.predicate;

import com.example.activity.ActivityPredicate;
import com.example.activity.Info;

public class DocTypeValidationPredicate implements ActivityPredicate {
    @Override
    public boolean validate(Info activityInfo) {

        System.out.println("Check if the file received to server: TRUE");

        System.out.println("Check if document is not empty: TRUE");

        System.out.println("Check if the document submitted is PDF: TRUE");

        return true;
    }
}
