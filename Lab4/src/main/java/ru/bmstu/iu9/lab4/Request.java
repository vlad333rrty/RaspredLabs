package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Request {
    private static final String JSON_PACKAGE_ID_FIELD_NAME ="package id";
    private static final String JSON_FUNCTION_FIELD_NAME="function";
    private static final String JSON_TESTS="tests";

    @JsonProperty(JSON_PACKAGE_ID_FIELD_NAME)
    private int packageId;
    @JsonProperty(JSON_FUNCTION_FIELD_NAME)
    private String function;
    @JsonProperty(JSON_TESTS)
    private ArrayList<String> tests;

    @JsonCreator
    public Request(@JsonProperty int packageId,)
}
