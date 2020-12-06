package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class SystemActor extends AbstractActor {
    private static final String SYSTEM_ACTOR_NAME ="routes";

    private Router router;

    @Override
    public Receive createReceive() {
        return null;
    }

    public SystemActor(){
        ActorSystem system=ActorSystem.create(SYSTEM_ACTOR_NAME);
        router=new Router(system);
    }

    public void run(){
        
    }
}
