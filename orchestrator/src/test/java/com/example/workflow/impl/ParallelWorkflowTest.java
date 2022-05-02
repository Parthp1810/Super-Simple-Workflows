package com.example.workflow.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.Status;
import com.example.activity.impl.DefaultContext;
import com.example.activity.impl.DefaultInfo;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.ParallelWorkflowBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

public class ParallelWorkflowTest {

    @Test
    @Ignore
    public void parallelWorkflowExecute() {
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        List<Activity> activityList = new ArrayList<>();
        activityList.add(testActivityTwo);
        activityList.add(testActivityThree);
        Context activityContext = new DefaultContext();
        Mockito.when(testActivityOne.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));

        ParallelWorkflow parallelWorkflow = new ParallelWorkflowBuilder()
                .id("Test")
                .execute(testActivityOne)
                .execute(activityList)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        orchestrator.execute(parallelWorkflow, activityContext);

        Mockito.verify(testActivityOne, times(1)).execute(activityContext);
        Mockito.verify(testActivityTwo, times(1)).execute(activityContext);
        Mockito.verify(testActivityThree, times(1)).execute(activityContext);
    }
}
