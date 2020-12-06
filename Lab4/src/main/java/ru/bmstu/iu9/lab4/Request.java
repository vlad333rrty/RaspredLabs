package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Request {
    private static final String JSON_PACKAGE_ID_FIELD="package id";

    @JsonProperty
    private int packageId;
    @JsonProperty
    private String function;
    @JsonProperty
    private ArrayList<String> tests;
}
