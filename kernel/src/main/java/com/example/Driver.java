package com.example;


import com.example.configuration.Configuration;
import com.example.sample.workflow.ATMWorkflow;
import com.example.sample.workflow.InvestmentWorkflow;
import com.example.sample.workflow.PlagiarismWorkflow;

public class Driver {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setupConfiguration();

        ATMWorkflow workflow=new ATMWorkflow();
        workflow.run(200);

        InvestmentWorkflow investmentWorkflow = new InvestmentWorkflow();
        investmentWorkflow.run();
        System.out.println();

        PlagiarismWorkflow plagiarismWorkflow = new PlagiarismWorkflow();
        plagiarismWorkflow.run();
    }
}
