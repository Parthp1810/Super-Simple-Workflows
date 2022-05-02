package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.workflow.impl.LinearWorkflow;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class LinearWorkflowBuilderTest {

    @Test
    public void linearWorkflowBuilderTest() {
        String workflowId = "WORKFLOW_ID";
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);

        LinearWorkflow linearWorkflow = new LinearWorkflowBuilder()
                .id(workflowId)
                .execute(testActivityOne)
                .then(testActivityTwo)
                .build();

        Assert.assertEquals(workflowId, linearWorkflow.getId());
    }
}
