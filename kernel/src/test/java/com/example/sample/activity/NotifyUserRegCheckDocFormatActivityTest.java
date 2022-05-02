package com.example.sample.activity;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.impl.DefaultContext;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.LinearWorkflowBuilder;
import com.example.workflow.impl.LinearWorkflow;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class NotifyUserRegCheckDocFormatActivityTest {

    @Test
    public void testExecute() {

        Activity testActivity = Mockito.mock(NotifyUserRegCheckDocFormatActivity.class);
        Activity demoActivity = Mockito.mock(Activity.class);
        Context activityContext = new DefaultContext();

        LinearWorkflow Workflow = new LinearWorkflowBuilder()
                .id("Workflow")
                .execute(testActivity)
                .then(demoActivity)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        orchestrator.execute(Workflow, activityContext);

        Mockito.verify(testActivity, times(1)).execute(activityContext);
        Mockito.verify(demoActivity, times(1)).execute(activityContext);
    }

}