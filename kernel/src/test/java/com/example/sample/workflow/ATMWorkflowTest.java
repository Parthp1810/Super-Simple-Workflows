package com.example.sample.workflow;

import com.example.activity.*;
import com.example.activity.impl.DefaultContext;
import com.example.activity.impl.DefaultInfo;
import com.example.activity.impl.DefaultStateMachineActivity;
import com.example.conductor.Orchestrator;
import com.example.conductor.impl.OrchestratorImpl;
import com.example.workflow.builder.ConditionalWorkflowBuilder;
import com.example.workflow.builder.LinearWorkflowBuilder;
import com.example.workflow.builder.ParallelWorkflowBuilder;
import com.example.workflow.builder.RecurrentWorkflowBuilder;
import com.example.workflow.impl.ConditionalWorkflow;
import com.example.workflow.impl.LinearWorkflow;
import com.example.workflow.impl.ParallelWorkflow;
import com.example.workflow.impl.RecurrentWorkflow;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.times;

public class ATMWorkflowTest {

    @Test
    public void testWithdrawSuccessfulExecute()
    {
        Activity idleConditionActivity = Mockito.mock(Activity.class);
        Activity cardReadActivity = Mockito.mock(Activity.class);
        Activity notifyUnsuccessfulReadActivity = Mockito.mock(Activity.class);
        Activity pinEntryActivity = Mockito.mock(Activity.class);
        Activity notifyReEnterPinActivity = Mockito.mock(Activity.class);
        Activity optionActivity = Mockito.mock(Activity.class);
        Activity balanceActivity = Mockito.mock(Activity.class);
        Activity balanceVerificationActivity = Mockito.mock(Activity.class);
        Activity notifyFailureActivity = Mockito.mock(Activity.class);
        Activity paymentActivity = Mockito.mock(Activity.class);

        Activity notifySuccessActivity = Mockito.mock(Activity.class);

        ActivityPredicate readPredicate = activityInfo -> true;
        ActivityPredicate pinVerificationPredicate = activityInfo -> true;
        ActivityPredicate optionPredicate = activityInfo -> true;
        ActivityPredicate paymentPredicate = activityInfo -> true;
        DefaultStateMachineActivity allocationActivity = Mockito.mock(DefaultStateMachineActivity.class);
        Context activityContext = new DefaultContext();
        Mockito.when(allocationActivity.execute(activityContext)).thenReturn(new DefaultInfo(Status.SUCCESS,activityContext));
        ParallelWorkflow parallelWorkflow = new ParallelWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(allocationActivity)
                .execute(notifySuccessActivity)
                .build();
        LinearWorkflow fulfilmentWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(paymentActivity)
                .then(parallelWorkflow)
                .build();
        RecurrentWorkflow fulfilmentWithdrawWorkflow = new RecurrentWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(fulfilmentWorkflow)
                .count(2)
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
                .id("ATM")
                .execute(idleConditionActivity)
                .then(CardReadWorkflow)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();

        orchestrator.execute(ATMWorkflow, activityContext);

        Mockito.verify(idleConditionActivity, times(1)).execute(activityContext);
        Mockito.verify(cardReadActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUnsuccessfulReadActivity, times(0)).execute(activityContext);
        Mockito.verify(pinEntryActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyReEnterPinActivity, times(0)).execute(activityContext);
        Mockito.verify(optionActivity, times(1)).execute(activityContext);
        Mockito.verify(balanceActivity, times(0)).execute(activityContext);
        Mockito.verify(balanceVerificationActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyFailureActivity, times(0)).execute(activityContext);
        Mockito.verify(paymentActivity, times(2)).execute(activityContext);

    }
    @Test
    public void testPaymentFailExecute()
    {

        Activity idleConditionActivity = Mockito.mock(Activity.class);
        Activity cardReadActivity = Mockito.mock(Activity.class);
        Activity notifyUnsuccessfulReadActivity = Mockito.mock(Activity.class);
        Activity pinEntryActivity = Mockito.mock(Activity.class);
        Activity notifyReEnterPinActivity = Mockito.mock(Activity.class);
        Activity optionActivity = Mockito.mock(Activity.class);
        Activity balanceActivity = Mockito.mock(Activity.class);
        Activity balanceVerificationActivity = Mockito.mock(Activity.class);
        Activity notifyFailureActivity = Mockito.mock(Activity.class);
        Activity paymentActivity = Mockito.mock(Activity.class);

        ActivityPredicate readPredicate = activityInfo -> true;
        ActivityPredicate pinVerificationPredicate = activityInfo -> true;
        ActivityPredicate optionPredicate = activityInfo -> true;
        ActivityPredicate paymentPredicate = activityInfo -> false;

        ConditionalWorkflow WithdrawWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(balanceVerificationActivity)
                .when(paymentPredicate)
                .then(paymentActivity)
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
                .id("ATM")
                .execute(idleConditionActivity)
                .then(CardReadWorkflow)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(ATMWorkflow, activityContext);

        Mockito.verify(idleConditionActivity, times(1)).execute(activityContext);
        Mockito.verify(cardReadActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUnsuccessfulReadActivity, times(0)).execute(activityContext);
        Mockito.verify(pinEntryActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyReEnterPinActivity, times(0)).execute(activityContext);
        Mockito.verify(optionActivity, times(1)).execute(activityContext);
        Mockito.verify(balanceActivity, times(0)).execute(activityContext);
        Mockito.verify(balanceVerificationActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyFailureActivity, times(1)).execute(activityContext);
        Mockito.verify(paymentActivity, times(0)).execute(activityContext);
    }
    @Test
    public void testPinVerificationFailExecute()
    {

        Activity idleConditionActivity = Mockito.mock(Activity.class);
        Activity cardReadActivity = Mockito.mock(Activity.class);
        Activity notifyUnsuccessfulReadActivity = Mockito.mock(Activity.class);
        Activity pinEntryActivity = Mockito.mock(Activity.class);
        Activity notifyReEnterPinActivity = Mockito.mock(Activity.class);
        Activity optionActivity = Mockito.mock(Activity.class);

        ActivityPredicate readPredicate = activityInfo -> true;
        ActivityPredicate pinVerificationPredicate = activityInfo -> false;

        LinearWorkflow notifyReEnterPinWorkflow;
        ConditionalWorkflow PinEntryWorkflow = null;
        notifyReEnterPinWorkflow = new LinearWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(notifyReEnterPinActivity)
                .then(optionActivity)
                .build();
        PinEntryWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(pinEntryActivity)
                .when(pinVerificationPredicate)
                .then(optionActivity)
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
                .id("ATM")
                .execute(idleConditionActivity)
                .then(CardReadWorkflow)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(ATMWorkflow, activityContext);

        Mockito.verify(idleConditionActivity, times(1)).execute(activityContext);
        Mockito.verify(cardReadActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUnsuccessfulReadActivity, times(0)).execute(activityContext);
        Mockito.verify(pinEntryActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyReEnterPinActivity, times(1)).execute(activityContext);
        Mockito.verify(optionActivity, times(1)).execute(activityContext);

    }
    @Test
    public void testBalanceSuccessfulExecute()
    {

        Activity idleConditionActivity = Mockito.mock(Activity.class);
        Activity cardReadActivity = Mockito.mock(Activity.class);
        Activity notifyUnsuccessfulReadActivity = Mockito.mock(Activity.class);
        Activity pinEntryActivity = Mockito.mock(Activity.class);
        Activity notifyReEnterPinActivity = Mockito.mock(Activity.class);
        Activity optionActivity = Mockito.mock(Activity.class);
        Activity balanceActivity = Mockito.mock(Activity.class);
        Activity balanceVerificationActivity = Mockito.mock(Activity.class);
        Activity notifyFailureActivity = Mockito.mock(Activity.class);

        ActivityPredicate readPredicate = activityInfo -> true;
        ActivityPredicate pinVerificationPredicate = activityInfo -> true;
        ActivityPredicate optionPredicate = activityInfo -> false;

        ConditionalWorkflow WithdrawWorkflow = null;
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
                .id("ATM")
                .execute(idleConditionActivity)
                .then(CardReadWorkflow)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(ATMWorkflow, activityContext);

        Mockito.verify(idleConditionActivity, times(1)).execute(activityContext);
        Mockito.verify(cardReadActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUnsuccessfulReadActivity, times(0)).execute(activityContext);
        Mockito.verify(pinEntryActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyReEnterPinActivity, times(0)).execute(activityContext);
        Mockito.verify(optionActivity, times(1)).execute(activityContext);
        Mockito.verify(balanceActivity, times(1)).execute(activityContext);
        Mockito.verify(balanceVerificationActivity, times(0)).execute(activityContext);
        Mockito.verify(notifyFailureActivity, times(0)).execute(activityContext);
    }
    @Test
    public void testCardReadFailExecute()
    {

        Activity idleConditionActivity = Mockito.mock(Activity.class);
        Activity cardReadActivity = Mockito.mock(Activity.class);
        Activity notifyUnsuccessfulReadActivity = Mockito.mock(Activity.class);
        Activity pinEntryActivity = Mockito.mock(Activity.class);

        ActivityPredicate readPredicate = activityInfo -> false;

        ConditionalWorkflow CardReadWorkflow = new ConditionalWorkflowBuilder()
                .id(UUID.randomUUID().toString())
                .execute(cardReadActivity)
                .when(readPredicate)
                .then(pinEntryActivity)
                .orElse(notifyUnsuccessfulReadActivity)
                .build();
        LinearWorkflow ATMWorkflow = new LinearWorkflowBuilder()
                .id("ATM")
                .execute(idleConditionActivity)
                .then(CardReadWorkflow)
                .build();

        Orchestrator orchestrator = new OrchestratorImpl();
        Context activityContext = new DefaultContext();
        orchestrator.execute(ATMWorkflow, activityContext);

        Mockito.verify(idleConditionActivity, times(1)).execute(activityContext);
        Mockito.verify(cardReadActivity, times(1)).execute(activityContext);
        Mockito.verify(notifyUnsuccessfulReadActivity, times(1)).execute(activityContext);
        Mockito.verify(pinEntryActivity, times(0)).execute(activityContext);
    }
}
