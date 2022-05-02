package com.example.conductor.impl;

import com.example.conductor.Orchestrator;
import com.example.activity.Context;
import com.example.activity.Info;
import com.example.workflow.Workflow;

public class OrchestratorImpl implements Orchestrator {
    @Override
    public Info execute(Workflow workFlow, Context activityContext) {
        return workFlow.execute(activityContext);
    }
}
