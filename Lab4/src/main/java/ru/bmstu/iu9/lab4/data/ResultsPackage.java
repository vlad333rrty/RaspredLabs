package ru.bmstu.iu9.lab4.data;

import java.util.List;

public class ResultsPackage {
    private final List<TestResult> results;
    private final int packageId;

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
