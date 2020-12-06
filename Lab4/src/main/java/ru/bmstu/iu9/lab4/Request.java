package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Request {
    private static final String JSON_PACKAGE_ID_FIELD_NAME ="package id";
    private static final String JSON_CODE="code";
    private static final String JSON_FUNCTION_FIELD_NAME="function name";
    private static final String JSON_TESTS="tests";

    private int packageId;
    private String code;
    private String functionName;
    private ArrayList<String> tests;

    @JsonCreator
    public Request(@JsonProperty(JSON_PACKAGE_ID_FIELD_NAME) int packageId,
                   @JsonProperty(JSON_CODE) String code,
                   @JsonProperty(JSON_FUNCTION_FIELD_NAME) String functionName,
                   @JsonProperty(JSON_TESTS) ArrayList<String> tests){
        this.packageId=packageId;
        this.code=code;
        this.functionName = functionName;
        this.tests=tests;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public ArrayList<Test> getTests() {
        ArrayList<Test> testList=new ArrayList<>();
        for (String s:tests){
            
        }
        return testList;
    }

    public class Test{
        private static final String PASSED_VALUE="passed";
        private static final String EXPECTED_VALUE="expected";

        private int passedValue,expectedResult;

        public Test(@JsonProperty(PASSED_VALUE) int passedValue,
                    @JsonProperty(EXPECTED_VALUE) int expectedResult){
            this.passedValue=passedValue;
            this.expectedResult=expectedResult;
        }
    }
}
