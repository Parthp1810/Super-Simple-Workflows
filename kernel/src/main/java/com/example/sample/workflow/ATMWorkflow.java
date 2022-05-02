package com.example.sample.workflow;

import com.example.activity.ActivityPredicate;
import com.example.activity.Context;
import com.example.activity.impl.DefaultContext;
import com.example.activity.impl.DefaultStateMachineActivity;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.sample.activity.*;
import com.example.sample.predicate.OptionPredicate;
import com.example.sample.predicate.PaymentPredicate;
import com.example.sample.predicate.PinVerificationPredicate;
import com.example.sample.predicate.CardReadPredicate;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import com.example.workflow.builder.LinearWorkflowBuilder;
import com.example.workflow.builder.RecurrentWorkflowBuilder;
import com.example.workflow.impl.ConditionalWorkflow;
import com.example.workflow.impl.LinearWorkflow;
import com.example.workflow.impl.RecurrentWorkflow;
import com.example.statemachine.Definition;
import com.example.statemachine.State;
import com.example.statemachine.StateMachine;
import com.example.statemachine.builder.DefinitionBuilder;
import com.example.statemachine.builder.MachineBuilder;
import com.example.statemachine.impl.DefaultTrigger;
import com.example.sample.action.AllocationAction;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class ATMWorkflow {

    public void run(int bal) {


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

        CardReadActivity cardReadActivity = new CardReadActivity();
        NotifyUnsuccessfulReadActivity notifyUnsuccessfulReadActivity = new NotifyUnsuccessfulReadActivity();
        PinEntryActivity pinEntryActivity = new PinEntryActivity();
        OptionActivity optionActivity = new OptionActivity();
        BalanceActivity balanceActivity = new BalanceActivity();
        NotifyReEnterPinActivity notifyReEnterPinActivity = new NotifyReEnterPinActivity();

        ActivityPredicate readPredicate = new CardReadPredicate();
        ActivityPredicate optionPredicate = new OptionPredicate();
        ActivityPredicate pinVerificationPredicate = new PinVerificationPredicate();

        BalanceVerificationActivity balanceVerificationActivity = new BalanceVerificationActivity();

        PaymentActivity paymentActivity = new PaymentActivity();
        NotifySuccessActivity notifySuccessActivity = new NotifySuccessActivity();
        NotifyFailureActivity notifyFailureActivity = new NotifyFailureActivity();
        IdleConditionActivity idleConditionActivity = new IdleConditionActivity();
        DefaultStateMachineActivity allocationActivity = new DefaultStateMachineActivity(stateMachine, allocationTrigger);
        Context activityContext = new DefaultContext();

        ActivityPredicate paymentPredicate = new PaymentPredicate();

        LinearWorkflow fulfilmentWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(paymentActivity)
                .then(notifySuccessActivity)
                .build();
        RecurrentWorkflow fulfilmentWithdrawWorkflow = new RecurrentWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(fulfilmentWorkflow)
                .count(bal/100)
                .build();
        ConditionalWorkflow WithdrawWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(balanceVerificationActivity)
                .when(paymentPredicate)
                .then(fulfilmentWithdrawWorkflow)
                .orElse(notifyFailureActivity)
                .build();
        ConditionalWorkflow OptionWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(optionActivity)
                .when(optionPredicate)
                .then(WithdrawWorkflow)
                .orElse(balanceActivity)
                .build();
        LinearWorkflow notifyReEnterPinWorkflow;
        ConditionalWorkflow PinEntryWorkflow = null;
        notifyReEnterPinWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(notifyReEnterPinActivity)
                .then(PinEntryWorkflow)
                .build();
        PinEntryWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(pinEntryActivity)
                .when(pinVerificationPredicate)
                .then(OptionWorkflow)
                .orElse(notifyReEnterPinWorkflow)
                .build();
        ConditionalWorkflow CardReadWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(cardReadActivity)
                .when(readPredicate)
                .then(PinEntryWorkflow)
                .orElse(notifyUnsuccessfulReadActivity)
                .build();
        LinearWorkflow ATMWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(idleConditionActivity)
                .then(CardReadWorkflow)
                .build();
        Orchestrator orchestrator = new OrchestratorImpl();

        orchestrator.execute(ATMWorkflow, activityContext);
    }
}
