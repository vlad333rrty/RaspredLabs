package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Request {
    private static final String JSON_PACKAGE_ID_FIELD_NAME ="package id";
    private static final String JSON_FUNCTION_FIELD_NAME="function";
    private static final String JSON_TESTS="tests";

    private int packageId;
    private String function;
    private ArrayList<String> tests;

    @JsonCreator
    public Request(@JsonProperty(JSON_PACKAGE_ID_FIELD_NAME) int packageId,
                   @JsonProperty(JSON_FUNCTION_FIELD_NAME) String function,
                   @JsonProperty(JSON_TESTS) ArrayList<String> tests){
        this.packageId=packageId;
        this.function=function;
        this.tests=tests;
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
