package ru.bmstu.iu9.lab5.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.iu9.lab5.data.Request;
import ru.bmstu.iu9.lab5.data.RequestType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StoreActor extends AbstractActor {

    private final Map<String,Long> URLtoTime=new ConcurrentHashMap<>();

    private long getResult(String value){
        return URLtoTime.get(value);
    }

    private void addResult(String value,long result){
        URLtoTime.put(value,result);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Request.class, request->{
                    if (request.getType().equals(RequestType.GET_RESULT)){
                        sender().tell(getResult(request.getId()), ActorRef.noSender());
                    }else if (request.getType().equals(RequestType.ADD_RESULT)){
                        addResult(request.getId(),request.getResult());
                    }
                })
                .build();
    }
}
