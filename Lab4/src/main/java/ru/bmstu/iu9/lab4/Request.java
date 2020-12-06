package ru.bmstu.iu9.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Request {
    @JsonProperty
    private int packageId;
    @JsonProperty
    private String function;
    @JsonProperty
    private ArrayList<String> tests;
}
