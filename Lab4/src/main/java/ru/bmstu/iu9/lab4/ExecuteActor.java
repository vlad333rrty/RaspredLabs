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

    private void executeJSCode(String code,String functionName,int param) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        engine.eval(code);
        Invocable invocable = (Invocable) engine;
        invocable.invokeFunction(functionName,param)
    }
}
