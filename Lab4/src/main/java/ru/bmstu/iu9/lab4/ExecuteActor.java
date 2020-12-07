package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class ExecuteActor extends AbstractActor {
    private static final String ENGINE_NAME="nashron";
    private static final String STORAGE ="store";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Request.class,request -> {
                    ArrayList<TestResult> results=new ArrayList<>();
                    for (Test test:request.getTests()){
                        results.add(getTestResult(request.getCode(),request.getFunctionName(),
                                test.getParams(),test.getExpectedResult()));
                    }
                    getContext().actorSelection(STORAGE).tell(new ResultsPackage(results,request.getPackageId()), ActorRef.noSender());
                })
                .build();
    }

    private int executeJSCode(String code,String functionName,ArrayList<Integer> params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        engine.eval(code);
        Invocable invocable = (Invocable) engine;
        return (int) invocable.invokeFunction(functionName, params);
    }

    private TestResult getTestResult(int expectedResult,int result){
        TestResultStatus status;
        String description;
        if (result==expectedResult){
            status=TestResultStatus.SUCCEEDED;
            description="Test completed successfully";
        }else{
            status=TestResultStatus.FAILED;
            description="Wrong answer";
        }
        return new TestResult(status,description);
    }

    private TestResult getTestResult(String code,String functionName,ArrayList<Integer> params,int expectedResult){
        try{
            int result=executeJSCode(code,functionName,params);
            return getTestResult(expectedResult,result);
        }catch (ScriptException | NoSuchMethodException e){
            return new TestResult(TestResultStatus.FAILED,String.format("An exception occurred while testing:%s",e.getLocalizedMessage()));
        }
    }
}
