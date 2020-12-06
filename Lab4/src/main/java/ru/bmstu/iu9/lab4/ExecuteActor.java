package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExecuteActor extends AbstractActor {
    private static final String ENGINE_NAME="nashron";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Request.class,request -> {

                })
                .build();
    }

    private TestResult executeAndTestJSCode(String code,String functionName,int param,int expectedResult) {
        TestResultStatus status;
        String description;
        try{
            ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
            engine.eval(code);
            Invocable invocable = (Invocable) engine;
            int result=(int)invocable.invokeFunction(functionName,param);
            if (result==expectedResult){
                status=TestResultStatus.SUCCEEDED;
                description="Test finished successfully";
            }else{
                status=TestResultStatus.FAILED;
                description="Status: FAILED. Reason: Wrong result";
            }
            return new TestResult(status,description);
        }catch (Exception e){
            status=TestResultStatus.FAILED;
            description="An error occurred while testing";
            return new TestResult(status,description);
        }
    }

    private int executeJSCode(String code,String functionName,int param) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        engine.eval(code);
        Invocable invocable = (Invocable) engine;
        return (int) invocable.invokeFunction(functionName, param);
    }

    private TestResult getTestResult(String code,String functionName,int param,int expectedResult,int result){
        try {
            int result=executeJSCode(code,functionName,param);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
