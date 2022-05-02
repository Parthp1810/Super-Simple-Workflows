package com.example.sample.predicate;

import com.example.activity.Activity;
import com.example.activity.ActivityPredicate;
import com.example.activity.Context;
import com.example.activity.impl.DefaultContext;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import com.example.workflow.impl.ConditionalWorkflow;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class MetValidationPredicateTest {

    @Test
    public void testExecute() {

        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        Context activityContext = new DefaultContext();
        ActivityPredicate testPredicate = Mockito.mock(MetValidationPredicate.class);
        ConditionalWorkflow Workflow = new ConditionalWorkflowBuilder()
            .id("Workflow")
            .execute(testActivityOne)
            .when(testPredicate)
            .then(testActivityTwo)
            .orElse(testActivityThree)
            .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        orchestrator.execute(Workflow, activityContext);

        Mockito.verify(testActivityOne, times(1)).execute(activityContext);
        Mockito.verify(testActivityTwo, times(0)).execute(activityContext);
        Mockito.verify(testActivityThree, times(1)).execute(activityContext);
    }
}
