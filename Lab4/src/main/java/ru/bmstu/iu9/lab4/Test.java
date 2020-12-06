package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {
    private static final String JSON_PACKAGE_ID_FIELD_NAME ="package id";
    private static final String JSON_FUNCTION_FIELD_NAME="function";
    private static final String JSON_TESTS="tests";

    private int packageId;
    private String function;
    private int passed;
    private int expected;

    @JsonCreator
    public Test(@JsonProperty(JSON_PACKAGE_ID_FIELD_NAME) int packageId,
                   @JsonProperty(JSON_FUNCTION_FIELD_NAME) String function,
                   @JsonProperty(JSON_TESTS) int passed,
                @JsonProperty(JSON_TESTS) int expected){
        this.packageId=packageId;
        this.function=function;
        
    }

    public int getPackageId() {
        return packageId;
    }

    public String getFunction() {
        return function;
    }

    public ArrayList<String> getTests() {
        return tests;
    }
}
