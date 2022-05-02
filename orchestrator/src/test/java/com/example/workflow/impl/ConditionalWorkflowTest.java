package com.example.workflow.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.ActivityPredicate;
import com.example.activity.Status;
import com.example.activity.impl.DefaultContext;
import com.example.activity.impl.DefaultInfo;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.times;

public class ConditionalWorkflowTest {

    @Test
    public void testSuccessfulExecute() {
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        ActivityPredicate successPredicate = activityInfo -> true;

        ConditionalWorkflow conditionalWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(testActivityOne)
                .when(successPredicate)
                .then(testActivityTwo)
                .orElse(testActivityThree)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(conditionalWorkflow, activityContext);

        Mockito.verify(testActivityOne, times(1)).execute(activityContext);
        Mockito.verify(testActivityTwo, times(1)).execute(activityContext);
        Mockito.verify(testActivityThree, times(0)).execute(activityContext);

    }

    @Test
    public void testFailureExecute() {
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        ActivityPredicate failurePredicate = activityInfo -> false;

        ConditionalWorkflow conditionalWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(testActivityOne)
                .when(failurePredicate)
                .then(testActivityTwo)
                .orElse(testActivityThree)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(conditionalWorkflow, activityContext);

        Mockito.verify(testActivityOne, times(1)).execute(activityContext);
        Mockito.verify(testActivityTwo, times(0)).execute(activityContext);
        Mockito.verify(testActivityThree, times(1)).execute(activityContext);
    }
}
