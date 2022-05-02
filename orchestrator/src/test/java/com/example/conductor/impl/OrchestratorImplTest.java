package com.example.conductor.impl;

import com.example.activity.Context;
import com.example.conductor.Orchestrator;
import com.example.workflow.Workflow;
import org.junit.Test;
import org.mockito.Mockito;

public class OrchestratorImplTest {

    @Test
    public void executeTest() {
        Workflow workflow = Mockito.mock(Workflow.class);
        Context activityContext = Mockito.mock(Context.class);

        Orchestrator orchestrator = new OrchestratorImpl();
        orchestrator.execute(workflow, activityContext);
        Mockito.verify(workflow).execute(activityContext);
    }
}
