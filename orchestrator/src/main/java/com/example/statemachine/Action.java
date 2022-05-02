package com.example.statemachine;

import com.example.activity.Context;
import com.example.activity.Info;

public interface Action<T extends Trigger> {

    Info execute(Context context, T trigger);
}
