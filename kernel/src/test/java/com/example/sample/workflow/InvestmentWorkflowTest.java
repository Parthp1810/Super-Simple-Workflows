package com.example.sample.workflow;

import com.example.activity.ActivityPredicate;
import com.example.activity.Context;
import com.example.activity.impl.DefaultStateMachineActivity;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.persistence.MySqlContext;
import com.example.sample.activity.PaymentActivity;
import com.example.sample.activity.UserVerificationActivity;
import com.example.usecase.activity.NotificationActivity;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import com.example.workflow.builder.LinearWorkflowBuilder;
import com.example.workflow.impl.ConditionalWorkflow;
import com.example.workflow.impl.LinearWorkflow;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

public class InvestmentWorkflowTest {
    @Test
    public void investmentWorkflowTest(){
        PaymentActivity paymentActivity = Mockito.mock(PaymentActivity.class);
        NotificationActivity notificationActivity = Mockito.mock(NotificationActivity.class);
        DefaultStateMachineActivity allocationActivity = Mockito.mock(DefaultStateMachineActivity.class);
        UserVerificationActivity userVerificationActivity = Mockito.mock(UserVerificationActivity.class);
        ActivityPredicate paymentPredicate = Mockito.mock(ActivityPredicate.class);

        LinearWorkflow fulfilmentWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(paymentActivity)
                .then(allocationActivity)
                .build();

        ConditionalWorkflow orderWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(userVerificationActivity)
                .when(paymentPredicate)
                .then(fulfilmentWorkflow)
                .orElse(notificationActivity)
                .build();
        Context activityContext = Mockito.mock(MySqlContext.class);
        Orchestrator orchestrator = new OrchestratorImpl();
        orchestrator.execute(orderWorkflow, activityContext);

        Mockito.verify(activityContext,Mockito.times(0)).insert("e89b-42d3-a135","notification-context");
    }
}
