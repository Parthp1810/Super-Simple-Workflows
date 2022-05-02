package com.example.activity;

import com.example.activity.impl.DefaultContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ActivityInfoList implements Info {

    private final List<Info> infoList;

    public ActivityInfoList() {
        this(new ArrayList<>());
    }

    public ActivityInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    @Override
    public Status getStatus() {
        Status status = Status.SUCCESS;
        for (Info info: infoList) {
            if (Status.FAILURE.equals(info.getStatus())) {
                status = Status.FAILURE;
            }
        }
        return status;
    }

    @Override
    public Throwable getError() {
        Throwable throwable = null;
        for (Info info: infoList) {
            Throwable error = info.getError();
            if (Objects.nonNull(error)) {
                throwable = error;
            }
        }
        return throwable;
    }

    @Override
    public Context getContext() {
        Context activityContext = new DefaultContext();
        for (Info info: infoList) {
            Context eachActivityContext = info.getContext();
            for (Map.Entry<String, Object> entry: eachActivityContext.get()) {
                activityContext.insert(entry.getKey(), entry.getValue());
            }
        }
        return activityContext;
    }
}
