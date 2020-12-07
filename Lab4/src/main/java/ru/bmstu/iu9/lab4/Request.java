package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class Request {
    private static final String JSON_PACKAGE_ID_FIELD_NAME ="packageId";
    private static final String JSON_CODE="jsScript";
    private static final String JSON_FUNCTION_FIELD_NAME="functionName";
    private static final String JSON_TESTS="tests";

    @JsonProperty(JSON_PACKAGE_ID_FIELD_NAME)
    private int packageId;
    @JsonProperty(JSON_CODE)
    private String code;
    @JsonProperty(JSON_FUNCTION_FIELD_NAME)
    private String functionName;
    @JsonProperty(JSON_TESTS)
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

    public String getCode() {
        return code;
    }

    public ArrayList<Test> getTests() throws IOException {
        ArrayList<Test> testList=new ArrayList<>();
        for (String s:tests){
            Test test=new ObjectMapper().readerFor(Test.class).readValue(s);
            testList.add(test);
        }
        return testList;
    }

    public class Test{
        private static final String PARAMS ="params";
        private static final String EXPECTED_RESULT ="expectedResult";

        private int expectedResult;
        private ArrayList<Integer> params;

        public Test(@JsonProperty(PARAMS) ArrayList<Integer> params,
                    @JsonProperty(EXPECTED_RESULT) int expectedResult){
            this.params=params;
            this.expectedResult=expectedResult;
        }

        public ArrayList<Integer> getParams() {
            return params;
        }

        public int getExpectedResult() {
            return expectedResult;
        }
    }
}
