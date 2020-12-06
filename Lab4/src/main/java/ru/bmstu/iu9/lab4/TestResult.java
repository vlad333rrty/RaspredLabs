package ru.bmstu.iu9.lab4;

public class TestResult {
    private TestResultStatus status;
    private String description;
    public TestResult(TestResultStatus status,String description){
        this.status=status;
        this.description=description;
    }
}
