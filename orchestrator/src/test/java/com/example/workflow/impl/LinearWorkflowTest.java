package com.example.workflow.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.impl.DefaultContext;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.LinearWorkflowBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;

public class LinearWorkflowTest {

    @Test
    public void testExecute() {
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        List<Activity> activityList = new ArrayList<>();
        activityList.add(testActivityTwo);
        activityList.add(testActivityThree);

        LinearWorkflow linearWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(testActivityOne)
                .then(activityList)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(linearWorkflow, activityContext);

        Mockito.verify(testActivityOne, times(1)).execute(activityContext);
        Mockito.verify(testActivityTwo, times(1)).execute(activityContext);
        Mockito.verify(testActivityThree, times(1)).execute(activityContext);
    }

    @Test
    public void testMultipleExecute() {
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        LinearWorkflow linearWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(testActivityOne)
                .then(testActivityTwo)
                .then(testActivityThree)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(linearWorkflow, activityContext);

        Mockito.verify(testActivityOne, times(1)).execute(activityContext);
        Mockito.verify(testActivityTwo, times(1)).execute(activityContext);
        Mockito.verify(testActivityThree, times(1)).execute(activityContext);

    }
}
