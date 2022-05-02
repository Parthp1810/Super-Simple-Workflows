package com.example.workflow.impl;

import com.example.statemachine.StateMachine;
import com.example.workflow.Workflow;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
abstract class DefaultWorkflow implements Workflow {
    private final String id;
}
