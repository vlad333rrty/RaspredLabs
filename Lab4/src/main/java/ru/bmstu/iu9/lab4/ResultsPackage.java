package ru.bmstu.iu9.lab4;

import java.util.ArrayList;

public class ResultsPackage {
    private ArrayList<TestResult> results;
    private int packageId;

    public ResultsPackage(ArrayList<TestResult> results, int packageId) {
        this.results = results;
        this.packageId = packageId;
    }

    public ArrayList<TestResult> getResults() {
        return results;
    }

    public int getPackageId() {
        return packageId;
    }
}
