package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreActor extends AbstractActor {
    private final Map<Integer, List<TestResult>> packageIdToTest =new ConcurrentHashMap<>();

    public void storeTestResult(int packageId,TestResult testResult){
        if (packageIdToTest.containsKey(packageId)){
            packageIdToTest.get(packageId).add(testResult);
        }else{
            List<TestResult> list=new ArrayList<>();
            list.add(testResult);
            packageIdToTest.put(packageId,list);
        }
    }

    public List<TestResult> getTestResult(int packageId){
        return packageIdToTest.get(packageId);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(ResultsPackage.class, resultsPackage -> {
                    System.out.println("Test result received");
                    for (TestResult result:resultsPackage.getResults()){
                        storeTestResult(resultsPackage.getPackageId(),result);
                    }
                }).build();
    }
}
