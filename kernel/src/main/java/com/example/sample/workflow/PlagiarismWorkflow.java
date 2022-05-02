package com.example.sample.workflow;

import com.example.activity.Context;
import com.example.activity.impl.DefaultContext;
import com.example.activity.impl.DefaultStateMachineActivity;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.statemachine.Definition;
import com.example.statemachine.State;
import com.example.statemachine.StateMachine;
import com.example.statemachine.builder.DefinitionBuilder;
import com.example.statemachine.builder.MachineBuilder;
import com.example.statemachine.impl.DefaultTrigger;
import com.example.sample.action.SendReportToAdminAction;
import com.example.sample.activity.*;
import com.example.sample.predicate.DocTypeValidationPredicate;
import com.example.sample.predicate.MetValidationPredicate;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import com.example.workflow.builder.LinearWorkflowBuilder;
import com.example.workflow.builder.ParallelWorkflowBuilder;
import com.example.workflow.impl.ConditionalWorkflow;
import com.example.workflow.impl.LinearWorkflow;
import com.example.workflow.impl.ParallelWorkflow;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class PlagiarismWorkflow {

    public void run() {

        SubmitAssignmentActivity submitAssignmentActivity = new SubmitAssignmentActivity();

        State startState = new State("ALLOCATION_STARTED");
        State endState = new State("ALLOCATION_COMPLETED");

        DefaultTrigger allocationTrigger = new DefaultTrigger();

        Definition definition = new DefinitionBuilder()
                .id(UUID.randomUUID().toString())
                .from(startState)
                .to(endState)
                .trigger(DefaultTrigger.class)
                .action(new SendReportToAdminAction())
                .build();

        StateMachine stateMachine = new MachineBuilder(startState)
                .definitions(Collections.singletonList(definition))
                .states(Arrays.asList(startState, endState))
                .to(Collections.singletonList(endState))
                .build();

        DocTypeValidationActivity documentTypeValidationActivity = new DocTypeValidationActivity();
        DocTypeValidationPredicate docTypeValidationPredicate = new DocTypeValidationPredicate();
        SendMailConfirmationActivity sendMailConfirmationActivity = new SendMailConfirmationActivity();
        NotifyUserRegCheckDocFormatActivity notifyUserRegCheckDocFormatActivity = new NotifyUserRegCheckDocFormatActivity();

        PerformWordCountActivity performWordCountActivity = new PerformWordCountActivity();
        CheckPlagiarismPercentageActivity checkPlagarismPercentageActivity  = new CheckPlagiarismPercentageActivity();

        GenerateFinalReportActivity generateFinalReportActivity = new GenerateFinalReportActivity();

        PerformReportValidationActivity performReportValidationActivity = new PerformReportValidationActivity();
        MetValidationPredicate metValidationPredicate = new MetValidationPredicate();
        DefaultStateMachineActivity sendReportToAdminActivity = new DefaultStateMachineActivity(stateMachine, allocationTrigger);
        NotifyUserRegPlagiarismActivity notifyUserRegPlagiarismActivity = new NotifyUserRegPlagiarismActivity();

        ParallelWorkflow countAndCalculateWorkflow = new ParallelWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(performWordCountActivity)
                .execute(checkPlagarismPercentageActivity)
                .build();

        ConditionalWorkflow validateDocumentWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(documentTypeValidationActivity)
                .when(docTypeValidationPredicate)
                .then(sendMailConfirmationActivity)
                .orElse(notifyUserRegCheckDocFormatActivity)
                .build();

        ConditionalWorkflow validatePlagiarismWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(performReportValidationActivity)
                .when(metValidationPredicate)
                .then(sendReportToAdminActivity)
                .orElse(notifyUserRegPlagiarismActivity)
                .build();

        LinearWorkflow countPlagarismWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(submitAssignmentActivity)
                .then(validateDocumentWorkflow)
                .then(countAndCalculateWorkflow)
                .then(generateFinalReportActivity)
                .then(validatePlagiarismWorkflow)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(countPlagarismWorkflow, activityContext);
    }
}
