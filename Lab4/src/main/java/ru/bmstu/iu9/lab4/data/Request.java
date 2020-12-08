package ru.bmstu.iu9.lab4.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Request {
    private static final String PACKAGE_ID ="packageId";
    private static final String JS_SCRIPT ="jsScript";
    private static final String FUNCTION_NAME ="functionName";
    private static final String TESTS ="tests";

    @JsonProperty(PACKAGE_ID)
    private final int packageId;

    @JsonProperty(JS_SCRIPT)
    private final String code;

    @JsonProperty(FUNCTION_NAME)
    private final String functionName;

    @JsonProperty(TESTS)
    private final ArrayList<Test> tests;

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

    public ArrayList<Test> getTests() {
        return tests;
    }
}
