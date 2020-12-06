package ru.bmstu.iu9.lab4;

import java.util.List;

public class ResultsPackage {
    private List<TestResult> results;
    private int packageId;

    public ResultsPackage(List<TestResult> results, int packageId) {
        this.results = results;
        this.packageId = packageId;
    }

    public List<TestResult> getResults() {
        return results;
    }

    public int getPackageId() {
        return packageId;
    }
}
