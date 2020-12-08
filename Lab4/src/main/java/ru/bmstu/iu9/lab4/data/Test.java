package ru.bmstu.iu9.lab4.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test{
    private static final String PARAMS ="params";
    private static final String EXPECTED_RESULT ="expectedResult";
    private static final String TEST_NAME="testName";

    @JsonProperty(TEST_NAME)
    private String testName;

    @JsonProperty(EXPECTED_RESULT)
    private double expectedResult;

    @JsonProperty(PARAMS)
    private ArrayList<Integer> params;

    @JsonCreator
    public Test(@JsonProperty(PARAMS) ArrayList<Integer> params,
                @JsonProperty(EXPECTED_RESULT) double expectedResult,
                @JsonProperty(TEST_NAME) String testName){
        this.params=params;
        this.expectedResult=expectedResult;
        this.testName=testName;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public double getExpectedResult() {
        return expectedResult;
    }

    public String getTestName(){
        return testName;
    }
}