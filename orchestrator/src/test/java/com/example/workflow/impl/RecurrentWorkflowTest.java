package com.example.workflow.impl;

import com.example.activity.Activity;
import com.example.activity.Context;
import com.example.activity.impl.DefaultContext;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.RecurrentWorkflowBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.times;

public class RecurrentWorkflowTest {

    @Test
    public void testRecurrentExecute() {
        Activity activityOne = Mockito.mock(Activity.class);
        RecurrentWorkflow recurrentWorkflow = new RecurrentWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(activityOne)
                .count(2)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(recurrentWorkflow, activityContext);

        Mockito.verify(activityOne, times(2)).execute(activityContext);
    }
}
