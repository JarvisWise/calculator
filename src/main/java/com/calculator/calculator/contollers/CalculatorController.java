package com.calculator.calculator.contollers;


import com.calculator.calculator.services.Operation;
import com.calculator.calculator.services.OperationHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/calculator")
public class CalculatorController {

    private OperationHistory historyOf10;
    private final static int MAX_SIZE = 10;

    private final List<Operation> operations;

    public CalculatorController(OperationHistory historyOf10, List<Operation> operations) {
        this.historyOf10 = historyOf10;
        this.operations = operations;
    }

    @RequestMapping(path = "/add")
    public ResponseEntity<?> add(@RequestParam(value = "one", defaultValue = "1") double one,
                                            @RequestParam(value = "two", defaultValue = "2") double two) {

        for (Operation op: operations) {
            if ("add".equals(op.getName())) {
                double result = op.actionOfOperation(one, two);
                historyOf10.addOperations("Operation: add, result: " + result + "where(one = "+one+" , two = "+ two +")", MAX_SIZE);
                return new ResponseEntity("Result:" + result, HttpStatus.OK);
            }
        }
        return new ResponseEntity("Operation not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/subtract")
    public ResponseEntity<?> subtract(@RequestParam(value = "one", defaultValue = "1") double one,
                                            @RequestParam(value = "two", defaultValue = "2") double two) {
        for (Operation op: operations) {
            if ("substract".equals(op.getName())) {
                double result = op.actionOfOperation(one, two);
                historyOf10.addOperations("Operation: subtract, result: " + result + "where(one = "+one+" , two = "+ two +")", MAX_SIZE);
                return new ResponseEntity("Result:" + result, HttpStatus.OK);
            }
        }
        return new ResponseEntity("Operation not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/multiply")
    public ResponseEntity<?> multiply(@RequestParam(value = "one", defaultValue = "1") double one,
                                            @RequestParam(value = "two", defaultValue = "2") double two) {
        for (Operation op: operations) {
            if ("multiply".equals(op.getName())) {
                double result = op.actionOfOperation(one, two);
                historyOf10.addOperations("Operation: multiply, result: " + result + "where(one = "+one+" , two = "+ two +")", MAX_SIZE);
                return new ResponseEntity("Result:" + result, HttpStatus.OK);
            }
        }
        return new ResponseEntity("Operation not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/divide")
    public ResponseEntity<?> divide(@RequestParam(value = "one", defaultValue = "1") double one,
                                    @RequestParam(value = "two", defaultValue = "2") double two) {
        for (Operation op: operations) {
            if ("divide".equals(op.getName())) {

                if(two ==  0) {
                    return new ResponseEntity("Two cannot be 0!", HttpStatus.BAD_REQUEST);
                }

                double result = op.actionOfOperation(one, two);
                historyOf10.addOperations("Operation: divide, result: " + result + "where(one = "+one+" , two = "+ two +")", MAX_SIZE);
                return new ResponseEntity("Result:" + result, HttpStatus.OK);
            }
        }
        return new ResponseEntity("Operation not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/operations")
    public ResponseEntity<?> operations() {
        return new ResponseEntity(historyOf10.operationsQListToString(), HttpStatus.OK);
    }
}
