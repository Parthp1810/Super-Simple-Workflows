package com.example.activity;

import java.util.UUID;

public interface Activity {

    default String getId() {
        return UUID.randomUUID().toString();
    }

    Info execute(Context activityContext);
}
