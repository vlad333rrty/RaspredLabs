package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.ArrayList;

public class Request {
    private static final String PACKAGE_ID ="packageId";
    private static final String JS_SCRIPT ="jsScript";
    private static final String FUNCTION_NAME ="functionName";
    private static final String TESTS ="tests";

    @JsonProperty(PACKAGE_ID)
    private int packageId;

    @JsonProperty(JS_SCRIPT)
    private String code;

    @JsonProperty(FUNCTION_NAME)
    private String functionName;

    @JsonProperty(TESTS)
    private ArrayList<Test> tests;

    @JsonCreator
    public Request(@JsonProperty(PACKAGE_ID) int packageId,
                   @JsonProperty(JS_SCRIPT) String code,
                   @JsonProperty(FUNCTION_NAME) String functionName,
                   @JsonProperty(TESTS) ArrayList<Test> tests){
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

    public String getCode() {
        return code;
    }

    public ArrayList<Test> getTests() throws IOException {
        return tests;
    }

    public class Test{
        private static final String PARAMS ="params";
        private static final String EXPECTED_RESULT ="expectedResult";
        private static final String TEST_NAME="testName";

        @JsonProperty(TEST_NAME)
        private String testName;

        @JsonProperty(EXPECTED_RESULT)
        private int expectedResult;

        @JsonProperty(PARAMS)
        private ArrayList<Integer> params;

        public Test(@JsonProperty(PARAMS) ArrayList<Integer> params,
                    @JsonProperty(EXPECTED_RESULT) int expectedResult,
                    @JsonProperty(TEST_NAME) String testName){
            this.params=params;
            this.expectedResult=expectedResult;
            this.testName=testName;
        }

        public ArrayList<Integer> getParams() {
            return params;
        }

        public int getExpectedResult() {
            return expectedResult;
        }

        public String getTestName(){
            return testName;
        }
    }
}
