package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.activity.ActivityPredicate;
import com.example.workflow.impl.ConditionalWorkflow;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class ConditionalWorkflowBuilderTest {

    @Test
    public void conditionalWorkflowBuilderTest() {
        String workflowId = "WORKFLOW_ID";
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        ActivityPredicate failurePredicate = activityInfo -> false;

        ConditionalWorkflow conditionalWorkflow = new ConditionalWorkflowBuilder()
                .id(workflowId)
                .execute(testActivityOne)
                .when(failurePredicate)
                .then(testActivityTwo)
                .orElse(testActivityThree)
                .build();

        Assert.assertEquals(workflowId, conditionalWorkflow.getId());
    }
}
