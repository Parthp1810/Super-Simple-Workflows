package com.example.workflow.builder;

import com.example.activity.Activity;
import com.example.workflow.impl.ParallelWorkflow;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class ParallelWorkflowBuilderTest {

    @Test
    public void parallelWorkflowBuilderTest() {
        String workflowId = "WORKFLOW_ID";
        Activity testActivityOne = Mockito.mock(Activity.class);
        Activity testActivityTwo = Mockito.mock(Activity.class);
        Activity testActivityThree = Mockito.mock(Activity.class);

        List<Activity> activityList = new ArrayList<>();
        activityList.add(testActivityTwo);
        activityList.add(testActivityThree);

        ParallelWorkflow parallelWorkflow = new ParallelWorkflowBuilder()
                .id(workflowId)
                .execute(testActivityOne)
                .execute(activityList)
                .build();

        Assert.assertEquals(workflowId, parallelWorkflow.getId());
    }
}
