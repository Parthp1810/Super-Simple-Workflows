package com.example.activity.impl;

import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.Status;


public class DefaultInfo implements Info {

    private final Status status;
    private final Context context;
    private Throwable error;

    public DefaultInfo(Status status, Context context, Throwable error) {
        this.status = status;
        this.context = context;
        this.error = error;
    }

    public DefaultInfo(Status status, Context context) {
        this.status = status;
        this.context = context;
    }

    public Status getStatus() {
        return status;
    }

    public Context getContext() {
        return context;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
