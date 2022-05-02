package com.example.workflow.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.activity.ActivityInfoList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelWorkflow extends DefaultWorkflow {
    private final List<Activity> activities = new ArrayList<>();

    public ParallelWorkflow(String name, List<Activity> activities) {
        super(name);
        this.activities.addAll(activities);
    }

    @Override
    public ActivityInfoList execute(Context activityContext) {
        List<Info> activityInfoList = new ArrayList<>();

        for (Activity activity: activities) {
            ExecutorService executor = Executors.newFixedThreadPool(20);
            Runnable task = () ->{
                try {
                    Info activityInfo = activity.execute(activityContext);
                    activityInfoList.add(activityInfo);
                }
                catch (Exception exception){
                    throw exception;
                }
            };
            executor.execute(task);
        }
        return new ActivityInfoList(activityInfoList);
    }

    // Builder for Parallel Workflow
    public static class Builder {

        private Builder() {
        }

        public static Id createWorkflow() {
            return new Steps();
        }

        public interface Execute {
            Execute execute(Activity activity);
            Execute execute(List<Activity> activity);
            ParallelWorkflow build();
        }

        public interface Id extends Execute {
            Execute id(String id);
        }

        private static class Steps implements Id, Execute {

            private String id;
            private final List<Activity> activityList;

            Steps() {
                this.id = UUID.randomUUID().toString();
                this.activityList = new ArrayList<>();
            }

            public Execute id(String id) {
                this.id = id;
                return this;
            }

            @Override
            public Execute execute(Activity activity) {
                this.activityList.add(activity);
                return this;
            }

            @Override
            public Execute execute(List<Activity> activities) {
                this.activityList.addAll(activities);
                return this;
            }

            @Override
            public ParallelWorkflow build() {
                return new ParallelWorkflow(this.id, this.activityList);
            }

        }
    }
}
