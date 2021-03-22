package com.calculator.calculator.services;


import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class OperationHistory {

    private ArrayDeque<String> operationsQList = new ArrayDeque<String>();

    public Queue<String> getOperationsQList() {
        return operationsQList;
    }

    public void addOperations(String operationText, int maxSize) {
        if (maxSize <= 0) {
           return;
        }
        while(operationsQList.size() >= maxSize) {
            operationsQList.removeFirst();
        }
        operationsQList.addLast(operationText);

    }

    public String operationsQListToString() {
        StringBuilder text = new StringBuilder();
        text.append("Size = ").append(operationsQList.size()).append(" Operations: ");
        for (String str: operationsQList) {
            text.append(str).append("; ");
        }
        return  text.toString();
    }
}
