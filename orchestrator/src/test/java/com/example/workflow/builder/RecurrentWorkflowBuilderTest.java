package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.workflow.impl.RecurrentWorkflow;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class RecurrentWorkflowBuilderTest {

    @Test
    public void recurrentWorkflowBuilderTest() {
        String workflowId = "WORKFLOW_ID";
        Activity activityOne = Mockito.mock(Activity.class);
        RecurrentWorkflow recurrentWorkflow = new RecurrentWorkflowBuilder()
                .id(workflowId)
                .execute(activityOne)
                .count(2)
                .build();

        Assert.assertEquals(workflowId, recurrentWorkflow.getId());
    }
}
