package com.example.sample.workflow;

import com.example.activity.Activity;
import com.example.activity.ActivityPredicate;
import com.example.activity.Context;
import com.example.activity.Status;
import com.example.activity.impl.DefaultContext;
import com.example.activity.impl.DefaultInfo;
import com.example.activity.impl.DefaultStateMachineActivity;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import com.example.workflow.builder.LinearWorkflowBuilder;
import com.example.workflow.builder.ParallelWorkflowBuilder;
import com.example.workflow.impl.ConditionalWorkflow;
import com.example.workflow.impl.LinearWorkflow;
import com.example.workflow.impl.ParallelWorkflow;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.times;

public class PlagiarismWorkflowTest {

    @Test
    public void testSendLowPlagiarismDocumentToAdmin() {

        Activity submitAssignmentActivity = Mockito.mock(Activity.class);
        Activity documentTypeValidationActivity = Mockito.mock(Activity.class);
        Activity sendMailConfirmationActivity = Mockito.mock(Activity.class);
        Activity notifyUserRegCheckDocFormatActivity = Mockito.mock(Activity.class);
        Activity performWordCountActivity = Mockito.mock(Activity.class);
        Activity checkPlagarismPercentageActivity = Mockito.mock(Activity.class);
        Activity generateFinalReportActivity = Mockito.mock(Activity.class);
        Activity sendReportToAdminActivity = Mockito.mock(Activity.class);
        Activity notifyUserRegPlagiarismActivity = Mockito.mock(Activity.class);

        Activity performReportValidationActivity = Mockito.mock(Activity.class);

        ActivityPredicate docTypeValidationPredicate = activityInfo -> true;
        ActivityPredicate metValidationPredicate = activityInfo -> true;
        DefaultStateMachineActivity SendReportToAdminAction = Mockito.mock(DefaultStateMachineActivity.class);
        Context activityContext = new DefaultContext();
        Mockito.when(SendReportToAdminAction.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));

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
        orchestrator.execute(countPlagarismWorkflow, activityContext);

        Mockito.when(checkPlagarismPercentageActivity.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));
        Mockito.when(performWordCountActivity.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));
        Mockito.verify(submitAssignmentActivity, times(1)).execute(activityContext);
        Mockito.verify(documentTypeValidationActivity, times(1)).execute(activityContext);
        Mockito.verify(sendMailConfirmationActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUserRegCheckDocFormatActivity, times(0)).execute(activityContext);
        Mockito.verify(generateFinalReportActivity, times(1)).execute(activityContext);
        Mockito.verify(performReportValidationActivity, times(1)).execute(activityContext);
        Mockito.verify(sendReportToAdminActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUserRegPlagiarismActivity, times(0)).execute(activityContext);
    }

    @Test
    public void testRejectHighPlagiarismDocument() {

        Activity submitAssignmentActivity = Mockito.mock(Activity.class);
        Activity documentTypeValidationActivity = Mockito.mock(Activity.class);
        Activity sendMailConfirmationActivity = Mockito.mock(Activity.class);
        Activity notifyUserRegCheckDocFormatActivity = Mockito.mock(Activity.class);
        Activity performWordCountActivity = Mockito.mock(Activity.class);
        Activity checkPlagarismPercentageActivity = Mockito.mock(Activity.class);
        Activity generateFinalReportActivity = Mockito.mock(Activity.class);
        Activity sendReportToAdminActivity = Mockito.mock(Activity.class);
        Activity notifyUserRegPlagiarismActivity = Mockito.mock(Activity.class);

        Activity performReportValidationActivity = Mockito.mock(Activity.class);

        ActivityPredicate docTypeValidationPredicate = activityInfo -> true;
        ActivityPredicate metValidationPredicate = activityInfo -> false;
        DefaultStateMachineActivity SendReportToAdminAction = Mockito.mock(DefaultStateMachineActivity.class);
        Context activityContext = new DefaultContext();
        Mockito.when(SendReportToAdminAction.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));

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
        orchestrator.execute(countPlagarismWorkflow, activityContext);

        Mockito.when(checkPlagarismPercentageActivity.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));
        Mockito.when(performWordCountActivity.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));
        Mockito.verify(submitAssignmentActivity, times(1)).execute(activityContext);
        Mockito.verify(documentTypeValidationActivity, times(1)).execute(activityContext);
        Mockito.verify(sendMailConfirmationActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUserRegCheckDocFormatActivity, times(0)).execute(activityContext);
        Mockito.verify(generateFinalReportActivity, times(1)).execute(activityContext);
        Mockito.verify(performReportValidationActivity, times(1)).execute(activityContext);
        Mockito.verify(sendReportToAdminActivity, times(0)).execute(activityContext);
        Mockito.verify(notifyUserRegPlagiarismActivity, times(1)).execute(activityContext);
    }
}