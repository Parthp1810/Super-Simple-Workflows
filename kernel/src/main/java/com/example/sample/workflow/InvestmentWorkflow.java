package com.example.sample.workflow;

import com.example.activity.Context;
import com.example.activity.ActivityPredicate;
import com.example.activity.impl.DefaultStateMachineActivity;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.persistence.factory.ContextFactory;
import com.example.sample.action.AllocationAction;
import com.example.sample.activity.PaymentActivity;
import com.example.sample.activity.UserVerificationActivity;
import com.example.sample.predicate.PaymentPredicate;
import com.example.statemachine.Definition;
import com.example.statemachine.State;
import com.example.statemachine.StateMachine;
import com.example.statemachine.builder.DefinitionBuilder;
import com.example.statemachine.builder.MachineBuilder;
import com.example.statemachine.impl.DefaultTrigger;
import com.example.usecase.activity.NotificationActivity;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import com.example.workflow.builder.LinearWorkflowBuilder;
import com.example.workflow.impl.ConditionalWorkflow;
import com.example.workflow.impl.LinearWorkflow;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class InvestmentWorkflow {

    public void run() {

        UserVerificationActivity userVerificationActivity = new UserVerificationActivity();

        State startState = new State("ALLOCATION_STARTED");
        State endState = new State("ALLOCATION_COMPLETED");

        DefaultTrigger allocationTrigger = new DefaultTrigger();

        Definition definition = new DefinitionBuilder()
                .id(UUID.randomUUID().toString())
                .from(startState)
                .to(endState)
                .trigger(DefaultTrigger.class)
                .action(new AllocationAction())
                .build();

        StateMachine stateMachine = new MachineBuilder(startState)
                .definitions(Collections.singletonList(definition))
                .states(Arrays.asList(startState, endState))
                .to(Collections.singletonList(endState))
                .build();

        PaymentActivity paymentActivity = new PaymentActivity();
        DefaultStateMachineActivity allocationActivity = new DefaultStateMachineActivity(stateMachine, allocationTrigger);
        NotificationActivity notificationActivity = new NotificationActivity();
        ActivityPredicate paymentPredicate = new PaymentPredicate();

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

        Orchestrator orchestrator = new OrchestratorImpl();
        ContextFactory contextFactory = new ContextFactory();
        Context mongoDbContext = contextFactory.createContext("MONGODB", orderWorkflow.getId());
        Context mySqlContext = contextFactory.createContext("Mysql", orderWorkflow.getId());
        orchestrator.execute(orderWorkflow, mongoDbContext);
        orchestrator.execute(orderWorkflow, mySqlContext);
    }
}
