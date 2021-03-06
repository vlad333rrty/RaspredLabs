package ru.bmstu.iu9.lab4.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.iu9.lab4.data.ResultsPackage;
import ru.bmstu.iu9.lab4.data.TestResult;

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
                })
                .match(Integer.class,id -> {
                    System.out.println(getTestResult(id));
                    sender().tell(new ResultsPackage(getTestResult(id), id), ActorRef.noSender());
                })
                .build();
    }
}
