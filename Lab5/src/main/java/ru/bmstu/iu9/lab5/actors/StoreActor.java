package ru.bmstu.iu9.lab5.actors;

import akka.actor.AbstractActor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreActor extends AbstractActor {
    private final Map<String,Integer> URLtoInt=new ConcurrentHashMap<>();


    @Override
    public Receive createReceive() {
        return null;
    }


}
