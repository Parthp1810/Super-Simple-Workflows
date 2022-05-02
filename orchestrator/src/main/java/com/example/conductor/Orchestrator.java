package com.example.conductor;

import com.example.activity.Context;
import com.example.activity.Info;
import com.example.workflow.Workflow;

public interface Orchestrator {
    Info execute(Workflow workFlow, Context activityContext);
}
