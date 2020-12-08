package ru.bmstu.iu9.lab4.data;

public class TestResult {
    private final TestResultStatus status;
    private final String description;

    public TestResult(TestResultStatus status,String description){
        this.status=status;
        this.description=description;
    }

    public TestResultStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
